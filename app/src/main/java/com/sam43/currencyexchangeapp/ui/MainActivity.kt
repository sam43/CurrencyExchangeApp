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
import com.sam43.currencyexchangeapp.CurrencyApplication
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.databinding.ActivityMainBinding
import com.sam43.currencyexchangeapp.repository.MainViewModel
import com.sam43.currencyexchangeapp.ui.adapter.RecyclerViewAdapter
import com.sam43.currencyexchangeapp.utils.ConnectionLiveData
import com.sam43.currencyexchangeapp.utils.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: RecyclerViewAdapter
    lateinit var selectedItem: String

    private val viewModel: MainViewModel by viewModels()
    private val connectionViewModel: ConnectionLiveData by lazy { ConnectionLiveData(this) }

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

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onStart() {
        super.onStart()
        connectionViewModel.observeForever { isConnected ->
            CurrencyApplication.isNetworkConnected = isConnected
        }
    }

    private fun observeChanges() {
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collectLatest { event ->
                when(event) {
                    is MainViewModel.CurrencyEvent.SuccessResponse -> {
                        // checking because initially we will be getting result for 1 USD for conversion
                        initialCall()
                        //updateList(event.response?.rates?.let { getRatesAsList(it, amount.toDouble(), "USD") })
                    }
                    is MainViewModel.CurrencyEvent.ConnectionFailure -> {
                        whenFailedConnection(event)
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
                        @Suppress("UNCHECKED_CAST")
                        updateList(event.list as ArrayList<CurrencyRateItem>)
                    }
                    else -> Log.d(
                        TAG,
                        "onCreate() called with: event = $event"
                    )
                }
            }
        }
    }

    private fun whenFailedConnection(event: MainViewModel.CurrencyEvent.ConnectionFailure) {
        binding.progressBar.isVisible = true
        binding.tvResult.isVisible = false
        showLongToast(event.errorText)
    }

    private fun initialCall() {
        val amount = binding.etFrom.text.toString().ifEmpty { "1.0" }
        if (binding.etFrom.text.toString().isEmpty())
            viewModel.convert(amountStr = amount, from = selectedItem, to = null)
    }

    private fun updateList(list: MutableList<CurrencyRateItem>?) {
        if (list.isNullOrEmpty()) return
        binding.progressBar.isVisible = false
        @Suppress("UNCHECKED_CAST")
        mAdapter = RecyclerViewAdapter(list as ArrayList<CurrencyRateItem>)
        binding.rvGridView.adapter = mAdapter
        mAdapter.updateView()
        if (binding.etFrom.text.toString().isEmpty())
            defaultView("Initial BASE amount set to $selectedItem 1.0", Color.GREEN)
    }

    private fun whenFailed(event: MainViewModel.CurrencyEvent.Failure) {
        binding.progressBar.isVisible = false
        binding.tvResult.isVisible = true
        defaultView(event.errorText, Color.RED)
    }

    private fun defaultView(text: String, color: Int) {
        binding.tvResult.isVisible = true
        binding.tvResult.setTextColor(color)
        binding.tvResult.text = text
    }

    private fun whenLoading(event: MainViewModel.CurrencyEvent.Loading) {
        binding.progressBar.isVisible = true
        binding.tvResult.isVisible = false
    }
}