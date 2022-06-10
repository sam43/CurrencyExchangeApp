package com.sam43.currencyexchangeapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sam43.currencyexchangeapp.R
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem

class RecyclerViewAdapter(private val rates: ArrayList<CurrencyRateItem>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency_info, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rates[position])
    }
    override fun getItemCount(): Int {
        return rates.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateView() = notifyDataSetChanged()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CurrencyRateItem) {
            val tvCountry = itemView.findViewById<TextView>(R.id.tvCountry)
            val tvValue = itemView.findViewById<TextView>(R.id.tvRate)
            tvCountry.text = item.country.toString()
            tvValue.text = item.currency.toString()
        }
    }
}