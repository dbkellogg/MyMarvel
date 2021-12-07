package com.example.mymarvel.ui.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.mymarvel.R
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SearchActivity::class.java)

    //This is pretty hard to meaningfully UI test without considerably more time & effort
    //So you get this
    @Test
    fun smokeCheck() {
        onView(withId(R.id.comic_selection_title)).check(matches(isDisplayed()))
        onView(withId(R.id.comic_selection_image)).check(matches(isDisplayed()))
        onView(withId(R.id.comic_selection_details)).check(matches(isDisplayed()))
    }
}