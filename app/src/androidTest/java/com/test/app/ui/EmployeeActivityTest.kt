package com.test.app.ui

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.app.ShoppingActivity
import kotlinx.android.synthetic.main.main_activity.*
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class EmployeeActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(ShoppingActivity::class.java)


    @After
    fun cleanup() {
        rule.scenario.close()
    }

    @Test
    fun testFragmentLoaded() {
        rule.scenario.onActivity {
            val fragment = it.navHostFragment
            Assert.assertNotNull(fragment)
        }
    }
}
