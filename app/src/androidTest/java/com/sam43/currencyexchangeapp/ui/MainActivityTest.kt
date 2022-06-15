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
import com.sam43.currencyexchangeapp.utils.getRateForCurrency
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
    fun check_if_an_item_exists_in_recyclerview() {
        onView(withId(R.id.spFromCurrency)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("USD"))).perform(click())
        onView(withRecyclerView(R.id.rvGridView)
            .atPositionOnView(2, R.id.tvRate))
            .check(
                matches(withText("94.317"))
            )
    }

    @Test
    fun enter_conversion_amount_and_simulate_conversion() {
        // Type text and then press the button.
        val from = "USD"
        val to = "BDT"
        onView(withId(R.id.etFrom))
            .perform(typeText(toBeTypedText), closeSoftKeyboard())
        onView(withId(R.id.spFromCurrency)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(to))).perform(click())
        // Check that the conversion was changed or succeed.
        onView(withRecyclerView(R.id.rvGridView)
            .atPositionOnView(2, R.id.tvRate))
            .check(matches(withText((toBeTypedText.toDouble() * getRateForCurrency(from, dummyRatesAndroidTest())!!).to3decimalPoint())))
    }

    @After
    fun tearDown() {
        //clean up code
    }
}