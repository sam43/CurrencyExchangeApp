package com.sam43.currencyexchangeapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.databinding.ItemCurrencyInfoBinding

class RecyclerViewAdapter(private val rates: ArrayList<CurrencyRateItem>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCurrencyInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rates[position])
    }
    override fun getItemCount(): Int {
        return rates.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateView() = notifyDataSetChanged()

    class ViewHolder(private val itemCurrencyInfoBinding: ItemCurrencyInfoBinding) : RecyclerView.ViewHolder(itemCurrencyInfoBinding.root) {
        fun bind(item: CurrencyRateItem) {
            itemCurrencyInfoBinding.tvCountry.text = item.currency.toString()
            itemCurrencyInfoBinding.tvRate.text = item.value.toString()
        }
    }
}