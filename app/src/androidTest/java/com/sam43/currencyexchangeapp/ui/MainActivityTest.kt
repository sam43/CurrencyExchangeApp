package com.sam43.currencyexchangeapp.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sam43.currencyexchangeapp.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(androidx.test.runner.AndroidJUnit4::class)
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()

//    @Before
//    fun initialSetUp() {
//        init ofr
//    }

    @Test
    fun checkTheTitleTextIsNotNullOrEmpty() {
        onView(withId(R.id.tvTitle)).check(matches(withText(R.string.title)))
    }
}