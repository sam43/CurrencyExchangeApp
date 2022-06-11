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
import com.sam43.currencyexchangeapp.utils.to5decimalPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: RecyclerViewAdapter

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
        var selectedItem: String? = "USD"
        binding.spFromCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                selectedItem = parent?.getItemAtPosition(position) as String
                //parent.context.showLongToast("Selected Currency ${binding.etFrom.text.toString()}")
                if (binding.etFrom.text.toString().trim().isNotEmpty())
                    viewModel.convert(binding.etFrom.text.toString(), selectedItem)
                else
                    viewModel.convert("1.0", selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.btnConvert.setOnClickListener {
            viewModel.convert(binding.etFrom.text.toString())
        }
    }

    private fun observeChanges() {
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event) {
                    is MainViewModel.CurrencyEvent.SuccessResponse -> {
                        // checking because initially we will be getting result for 1 USD for conversion
                        val formInput: Double = if (binding.etFrom.text.toString().isEmpty()) 1.0 else binding.etFrom.text.toString().toDouble()
                        binding.progressBar.isVisible = false
                        mAdapter = RecyclerViewAdapter(event.response?.rates?.let { getRatesAsList(it, formInput) } as ArrayList<CurrencyRateItem>)
                        binding.rvGridView.adapter = mAdapter
                        mAdapter.updateView()
                        binding.tvResult.isVisible = false
                    }
                    is MainViewModel.CurrencyEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.isVisible = true
                        binding.tvResult.setTextColor(Color.RED)
                        binding.tvResult.text = event.errorText
                    }
                    is MainViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    else -> Log.d(
                        TAG,
                        "onCreate() called with: event = $event"
                    )
                }
            }
        }
    }

    private fun getRatesAsList(rates: Rates, amount: Double): MutableList<CurrencyRateItem> {
        return mutableListOf(CurrencyRateItem(country = "CAD", currency = (amount * rates.cAD!!).to5decimalPoint()),
        CurrencyRateItem(country = "HKD", currency = (amount * rates.hKD!!).to5decimalPoint()),
        CurrencyRateItem(country = "ISK", currency = (amount * rates.iSK!!).to5decimalPoint()),
        CurrencyRateItem(country = "BDT", currency = (amount * rates.bDT!!).to5decimalPoint()),
        CurrencyRateItem(country = "EUR", currency = (amount * rates.eUR!!).to5decimalPoint()),
        CurrencyRateItem(country = "PHP", currency = (amount * rates.pHP!!).to5decimalPoint()),
        CurrencyRateItem(country = "DKK", currency = (amount * rates.dKK!!).to5decimalPoint()),
        CurrencyRateItem(country = "HUF", currency = (amount * rates.hUF!!).to5decimalPoint()),
        CurrencyRateItem(country = "CZK", currency = (amount * rates.cZK!!).to5decimalPoint()),
        CurrencyRateItem(country = "AUD", currency = (amount * rates.aUD!!).to5decimalPoint()),
        CurrencyRateItem(country = "RON", currency = (amount * rates.rON!!).to5decimalPoint()),
        CurrencyRateItem(country = "SEK", currency = (amount * rates.sEK!!).to5decimalPoint()))
    }
}
