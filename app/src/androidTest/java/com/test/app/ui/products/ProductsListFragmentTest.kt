package com.test.app.ui.products


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.app.ShoppingActivity
import com.test.app.R
import com.test.app.ui.common.hasItemCount
import com.test.app.ui.common.viewAtPosition
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This class is for Android UI testing using Espresso.
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProductsListFragmentTest {

    @get:Rule
    val rule = ActivityScenarioRule(ShoppingActivity::class.java)

    @Before
    fun setup() {
        Thread.sleep(3000) //To load data on list.
    }

    @After
    fun cleanup() {
        rule.scenario.close()
    }

    @Test
    fun testProgressBar() {
        onView(withId(R.id.loading)).check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    fun testListVisible() {
        onView(withId(R.id.productsList)).check(matches(isDisplayed()))
    }

    @Test
    fun testListItemsCount() {
        onView(withId(R.id.productsList)).check(matches(hasItemCount(12)))
    }

    @Test
    fun testListFirstItem() {
        onView(withId(R.id.productsList))
            .check(matches(viewAtPosition(0, hasDescendant(withText("Nintendo Switch")))));
    }

    @Test
    fun testListLastItem() {  //first scroll till last and then check item.
        onView(withId(R.id.productsList))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(11))
            .check(matches(viewAtPosition(11, hasDescendant(withText("X-Bows Ergonomic")))));
    }

    @Test
    fun testListClick() {
        onView(withId(R.id.productsList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        //If on click something is shown/launch then we can assert that here.
    }
}

// Not writing unit test cases for CartFragment as that gonna be as same as this one.
// In this test app, intention is show the way of writing unit testcases rather than having full coder coverage.