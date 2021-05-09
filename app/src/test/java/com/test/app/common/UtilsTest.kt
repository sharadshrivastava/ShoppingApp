package com.test.app.common

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import com.test.app.ui.common.dpFromPx
import com.test.app.ui.common.pxFromDp
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UtilsTest {

    @Mock
    var context: Context? = null

    @Mock
    var resources: Resources? = null

    @Mock
    var displayMetrics: DisplayMetrics? = null

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(context?.resources).thenReturn(resources)
        Mockito.`when`(resources?.displayMetrics).thenReturn(displayMetrics)
        displayMetrics?.density = 5f
    }

    @Test
    fun testPxFromDp() {
        val expected = 80f
        val actual = pxFromDp(context, 16f)
        assertTrue(expected == actual)
    }

    @Test
    fun testDpFromPx() {
        val expected = 16f
        val actual = dpFromPx(context, 80f)
        assertTrue(expected == actual)
    }
}