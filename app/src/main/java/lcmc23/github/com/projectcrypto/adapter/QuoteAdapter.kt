package lcmc23.github.com.projectcrypto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lcmc23.github.com.projectcrypto.R
import lcmc23.github.com.projectcrypto.model.Quote

class QuotesAdapter(private val quotes: List<Quote>) : RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>() {
    class QuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewQuote: TextView = view.findViewById(R.id.textViewQuote)
        val textViewValue: TextView = view.findViewById(R.id.textViewValue)
        val textViewDate: TextView = view.findViewById(R.id.textViewDate)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
        return QuoteViewHolder(view)
    }
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.textViewQuote.text = quote.quote
        holder.textViewValue.text = quote.value.toString()
        holder.textViewDate.text = quote.date
    }
    override fun getItemCount() = quotes.size
}