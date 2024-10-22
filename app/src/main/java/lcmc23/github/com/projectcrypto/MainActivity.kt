package lcmc23.github.com.projectcrypto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import lcmc23.github.com.projectcrypto.state.ScreenState
import lcmc23.github.com.projectcrypto.viewmodel.CryptoViewModel
import lcmc23.github.com.projectcrypto.viewmodel.CryptoViewModelFactory

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val viewModel: CryptoViewModel by viewModels { CryptoViewModelFactory() }
    private val textViewBitcoin: TextView by lazy { findViewById(R.id.textViewBitcoin) }
    private val textViewDate: TextView by lazy { findViewById(R.id.textViewDate) }
    private val buttonRefresh: Button by lazy { findViewById(R.id.buttonRefresh) }
    private val buttonOpenQuotesActivity: Button by lazy { findViewById(R.id.buttonOpenQuote) }
    private val progressBar: ProgressBar by lazy { findViewById(R.id.progressBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbarMainActivity)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Crypto Monitor"

        viewModel.tickerLiveData.observe(this) { state: ScreenState ->
            when (state) {
                is ScreenState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    buttonRefresh.visibility = View.GONE
                }
                is ScreenState.Success -> {
                    progressBar.visibility = View.GONE
                    buttonRefresh.visibility = View.VISIBLE
                    textViewBitcoin.text =
                        NumberFormat.getCurrencyInstance(Locale("pt", "BR")).let {
                            it.format(state.data.last.toBigDecimal())
                        }
                    textViewDate.text = SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date())
                }
                is ScreenState.Error -> {
                    progressBar.visibility = View.GONE
                    buttonRefresh.visibility = View.VISIBLE
                    Toast.makeText(
                        this, "Ocorreu um erro", Toast
                            .LENGTH_LONG
                    ).show()
                }
            }
        }
        buttonRefresh.setOnClickListener {
            viewModel.refresh()
        }

        buttonOpenQuotesActivity.setOnClickListener {
            val intent = Intent(this, QuoteActivity::class.java)
            startActivity(intent)
        }
    }
}