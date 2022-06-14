package com.sam43.currencyexchangeapp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.databinding.ActivityMainBinding
import com.sam43.currencyexchangeapp.repository.MainViewModel
import com.sam43.currencyexchangeapp.ui.adapter.RecyclerViewAdapter
import com.sam43.currencyexchangeapp.utils.showLongToast
import com.sam43.currencyexchangeapp.utils.to3decimalPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: RecyclerViewAdapter
    lateinit var selectedItem: String

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        observeChanges()
    }

    private fun initViews() {
        binding.rvGridView.layoutManager = GridLayoutManager(this,3)
        binding.spFromCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                selectedItem = parent?.getItemAtPosition(position) as String
                if (binding.etFrom.text.toString().trim().isNotEmpty())
                    viewModel.convert(amountStr = binding.etFrom.text.toString(), from = selectedItem, to = null)
                else
                    viewModel.consumeRatesApi(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun observeChanges() {
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collectLatest { event ->
                when(event) {
                    is MainViewModel.CurrencyEvent.SuccessResponse -> {
                        // checking because initially we will be getting result for 1 USD for conversion
                        binding.progressBar.isVisible = false
                        viewModel.convert(amountStr = binding.etFrom.text.toString(), from = selectedItem, to = null)
                        binding.tvResult.isVisible = false
                    }
                    is MainViewModel.CurrencyEvent.ConnectionFailure -> {
                        binding.progressBar.isVisible = true
                        binding.tvResult.isVisible = false
                        showLongToast(event.errorText)
                    }
                    is MainViewModel.CurrencyEvent.Failure -> whenFailed(event)
                    is MainViewModel.CurrencyEvent.Loading -> whenLoading(event)
                    else -> Log.d(
                        TAG,
                        "onCreate() called with: event = $event"
                    )
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.conversionRates.collectLatest { event ->
                when(event) {
                    is MainViewModel.CurrencyEvent.Loading -> whenLoading(event)
                    is MainViewModel.CurrencyEvent.Failure -> whenFailed(event)
                    is MainViewModel.CurrencyEvent.SuccessListResponse<*> -> {
                        binding.progressBar.isVisible = false
                        @Suppress("UNCHECKED_CAST")
                        mAdapter = RecyclerViewAdapter(event.list as ArrayList<CurrencyRateItem>)
                        binding.rvGridView.adapter = mAdapter
                        mAdapter.updateView()
                        binding.tvResult.isVisible = false
                    }
                    else -> Log.d(
                        TAG,
                        "onCreate() called with: event = $event"
                    )
                }
            }
        }
    }

    private fun whenFailed(event: MainViewModel.CurrencyEvent.Failure) {
        binding.progressBar.isVisible = false
        binding.tvResult.isVisible = true
        binding.tvResult.setTextColor(Color.RED)
        binding.tvResult.text = event.errorText
    }

    private fun whenLoading(event: MainViewModel.CurrencyEvent.Loading) {
        binding.progressBar.isVisible = true
        binding.tvResult.isVisible = false
    }

    private fun getRatesAsList(rates: Rates, amount: Double): MutableList<CurrencyRateItem> {
        return mutableListOf(CurrencyRateItem(country = "CAD", currency = (amount * rates.cAD!!).to3decimalPoint()),
        CurrencyRateItem(country = "HKD", currency = (amount * rates.hKD!!).to3decimalPoint()),
        CurrencyRateItem(country = "ISK", currency = (amount * rates.iSK!!).to3decimalPoint()),
        CurrencyRateItem(country = "BDT", currency = (amount * rates.bDT!!).to3decimalPoint()),
        CurrencyRateItem(country = "EUR", currency = (amount * rates.eUR!!).to3decimalPoint()),
        CurrencyRateItem(country = "PHP", currency = (amount * rates.pHP!!).to3decimalPoint()),
        CurrencyRateItem(country = "DKK", currency = (amount * rates.dKK!!).to3decimalPoint()),
        CurrencyRateItem(country = "HUF", currency = (amount * rates.hUF!!).to3decimalPoint()),
        CurrencyRateItem(country = "CZK", currency = (amount * rates.cZK!!).to3decimalPoint()),
        CurrencyRateItem(country = "AUD", currency = (amount * rates.aUD!!).to3decimalPoint()),
        CurrencyRateItem(country = "RON", currency = (amount * rates.rON!!).to3decimalPoint()),
        CurrencyRateItem(country = "SEK", currency = (amount * rates.sEK!!).to3decimalPoint()))
    }
}