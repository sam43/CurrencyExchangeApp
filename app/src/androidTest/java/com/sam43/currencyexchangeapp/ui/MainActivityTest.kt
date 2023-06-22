package com.sam43.currencyexchangeapp.ui

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sam43.currencyexchangeapp.R
import com.sam43.currencyexchangeapp.dummyRatesAndroidTest
import com.sam43.currencyexchangeapp.ui.adapter.RecyclerViewAdapter
import com.sam43.currencyexchangeapp.utils.AppConstants
import com.sam43.currencyexchangeapp.utils.asMap
import com.sam43.currencyexchangeapp.utils.unitConvertedRate
import com.sam43.currencyexchangeapp.utils.to3decimalPoint
import com.sam43.currencyexchangeapp.withRecyclerView
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {
    @get:Rule var hiltRule = HiltAndroidRule(this)
    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()
    private val toBeTypedText = "105.79"

    @Before
    fun setUp() { hiltRule.inject() }

    @Test
    fun check_if_spinner_has_data_and_working() {
        onView(withId(R.id.spFromCurrency)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("BDT"))).perform(click())
        onView(withId(R.id.spFromCurrency)).check(matches(withSpinnerText(containsString("BDT"))))
    }

    @Test
    fun enter_conversion_amount_check_if_matches() {
        // Type text and then press the button.
        onView(withId(R.id.etFrom))
            .perform(clearText())
            .perform(typeText(toBeTypedText), closeSoftKeyboard())
        // Check that the text was changed.
        onView(withId(R.id.etFrom)).check(matches(withText(toBeTypedText)))
    }

    @Test(expected = PerformException::class)
    fun check_if_recyclerview_if_some_data_does_not_exist() {
        // Attempt to scroll to an item that contains the special text.
        onView(withId(R.id.rvGridView))
            // scrollTo will fail the test if no item matches.
            .perform(RecyclerViewActions.scrollTo<RecyclerViewAdapter.ViewHolder>(
                hasDescendant(withText("not in the list"))
            ))
    }

    @Test
    fun check_if_an_item_name_exists_in_recyclerview() {
        onView(withId(R.id.spFromCurrency)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(AppConstants.DEFAULT_CURRENCY))).perform(click())
        onView(withRecyclerView(R.id.rvGridView)
            .atPositionOnView(0, R.id.tvCountry))
            .check(
                matches(withText("AED")) // 1st item was AED
            )
    }

    @Test
    fun check_if_an_item_in_recyclerview_same_currency_should_show_same_as_input_value() {
        val defaultCurrency = "AED"
        onView(withId(R.id.etFrom))
            .perform(clearText())
            .perform(typeText(toBeTypedText), closeSoftKeyboard())
        onView(withId(R.id.spFromCurrency)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(defaultCurrency))).perform(click())
        println("default value: ${convertedValues(defaultCurrency, defaultCurrency)} and unit: ${unitConvertedRate(dummyRatesAndroidTest(), defaultCurrency, defaultCurrency)}")
        onView(withRecyclerView(R.id.rvGridView)
            .atPositionOnView(0, R.id.tvRate))
            .check(
                matches(withText(convertedValues(defaultCurrency, defaultCurrency))) // 1st item was AED
            )
    }

    @Test
    fun check_if_an_item_value_exists_in_recyclerview() {
        val currency = AppConstants.DEFAULT_CURRENCY
        onView(withId(R.id.spFromCurrency)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(currency))).perform(click())
        onView(withRecyclerView(R.id.rvGridView)
            .atPositionOnView(1, R.id.tvRate))
            .check(
                matches(withText(fetchDataByKey("AFN")))
            )
    }

    @Test
    fun check_if_an_bdt_value_exists_in_recyclerview() {
        val currency = AppConstants.DEFAULT_CURRENCY
        onView(withId(R.id.spFromCurrency)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(currency))).perform(click())
        onView(withRecyclerView(R.id.rvGridView)
            .atPositionOnView(12, R.id.tvRate))
            .check(
                matches(withText(fetchDataByKey("BDT")))
            )
    }

    private fun fetchDataByKey(key: String): String = dummyRatesAndroidTest().asMap()[key].toString()

    @Test
    fun enter_conversion_amount_and_simulate_conversion() {
        // Type text and then press the button.
        val from = AppConstants.DEFAULT_CURRENCY
        val to = "AED"
        onView(withId(R.id.etFrom))
            .perform(clearText())
            .perform(typeText(toBeTypedText), closeSoftKeyboard())
        onView(withId(R.id.spFromCurrency)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(from))).perform(click())
        // Check that the conversion was changed or succeed.
        onView(withRecyclerView(R.id.rvGridView)
            .atPositionOnView(0, R.id.tvRate))
            .check(matches(withText(convertedValues(from, to))))
    }
    @Test
    fun enter_conversion_amount_and_simulate_conversion_with_GBP() {
        // Type text and then press the button.
        val from = "AED"
        val to = "BDT"
        onView(withId(R.id.etFrom))
            .perform(clearText())
            .perform(typeText(toBeTypedText), closeSoftKeyboard())
        onView(withId(R.id.spFromCurrency)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(from))).perform(click())
        // Check that the conversion was changed or succeed.
        println("converted value: ${convertedValues(from, to)}")
        onView(withRecyclerView(R.id.rvGridView)
            .atPositionOnView(12, R.id.tvRate)) // because BDT is in 12th place
            .check(matches(withText(convertedValues(from, to))))
    }

    private fun convertedValues(from: String, to: String) =
        (toBeTypedText.toDouble() * unitConvertedRate(dummyRatesAndroidTest(), from, to)).toString()
}