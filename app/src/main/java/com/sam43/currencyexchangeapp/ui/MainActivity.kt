package com.sam43.currencyexchangeapp.ui

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.core.app.ActivityScenario.launch
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.databinding.ActivityMainBinding
import com.sam43.currencyexchangeapp.network.ApiConstants.DEFAULT_CURRENCY
import com.sam43.currencyexchangeapp.network.ApiConstants.DEFAULT_VALUE
import com.sam43.currencyexchangeapp.network.ApiConstants.INTERNET_CONNECTION_ERROR
import com.sam43.currencyexchangeapp.network.ConnectivityCheckerViewModel
import com.sam43.currencyexchangeapp.network.ConnectivityState
import com.sam43.currencyexchangeapp.repository.MainViewModel
import com.sam43.currencyexchangeapp.ui.adapter.RecyclerViewAdapter
import com.sam43.currencyexchangeapp.utils.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var isSucceededOnce: Boolean = false
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: RecyclerViewAdapter
    private var selectedItem: String = DEFAULT_CURRENCY

    private val viewModel: MainViewModel by viewModels()
    private val connectivityViewModel: ConnectivityCheckerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        observeChanges()
    }

    private fun initViews() {
        binding.etFrom.setText(DEFAULT_VALUE)
        textWatcherImpl()
        binding.rvGridView.layoutManager = GridLayoutManager(this,3)
        binding.spFromCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                selectedItem = parent?.getItemAtPosition(position) as String
                when {
                    binding.etFrom.text.toString().trim().isNotEmpty() ->
                        viewModel.convert(amountStr = binding.etFrom.text.toString(), from = selectedItem, to = null)
                    else -> initialCall(selectedItem)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun textWatcherImpl() {
        val watcher = object : TextWatcher {
            private var searchFor = ""

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return

                searchFor = searchText

                lifecycleScope.launch(Dispatchers.Main) {
                    delay(300)  //debounce timeOut
                    if (searchText != searchFor)
                        return@launch
                    viewModel.convert(amountStr = searchFor, from = selectedItem, to = null)
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        }
        binding.etFrom.addTextChangedListener(watcher)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            connectivityViewModel.connectivityState.collectLatest {
                when (it) {
                    ConnectivityState.ConnectionAvailable ->
                        initialCall(DEFAULT_CURRENCY)
                    ConnectivityState.ConnectionUnavailable ->
                        showLongToast(INTERNET_CONNECTION_ERROR)
                }
            }
        }
    }

    private fun observeChanges() {
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collectLatest { event ->
                when(event) {
                    is MainViewModel.CurrencyEvent.SuccessResponse -> {
                        // checking because initially we will be getting result for 1 USD for conversion
                        initialCall(selectedItem)
                        isSucceededOnce = true
                        //updateList(event.response?.rates?.let { getRatesAsList(it, amount.toDouble(), "USD") })
                    }
                    is MainViewModel.CurrencyEvent.ConnectionFailure -> whenFailedConnection(event)
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

    private fun initialCall(base: String) {
        val amount = binding.etFrom.text.toString().ifEmpty { DEFAULT_VALUE }
        if (binding.etFrom.text.toString().isEmpty()) {
            //viewModel.consumeRatesApi(defaultCurrency) // initialize with default value
            viewModel.convert(amountStr = amount, from = base, to = null)
        }
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
        binding.tvResult.text = event.toString().lowercase()
    }
}