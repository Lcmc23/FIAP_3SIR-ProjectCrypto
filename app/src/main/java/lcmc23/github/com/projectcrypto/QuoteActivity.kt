package lcmc23.github.com.projectcrypto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lcmc23.github.com.projectcrypto.adapter.QuotesAdapter
import lcmc23.github.com.projectcrypto.model.Quote

class QuoteActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val toolbar: Toolbar by lazy { findViewById(R.id.toolbarQuote) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val quotes = listOf(
            Quote("Modelo cotação 1", 1000.0, "01/01/2023 12:00"),
            Quote("Modelo cotação 2", 2000.0, "02/02/2023 12:00"),
            // Adicione mais dados mockados aqui
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = QuotesAdapter(quotes)
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}