package com.test.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.data.AppRepositoryImpl
import com.test.app.data.db.ProductsDao
import com.test.app.data.network.ProductsApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.rules.TestRule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    var mockWebServer = MockWebServer()

    /*
    DB testing will be done in "androidTest" pkg as that needs Context.
    We can get ApplicationProvider or InstrumentationRegistry only in "androidTest" pkg.
    Returning null from here for satisfying repository param.
    */
    private val dao: ProductsDao? = null

    val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("").toString())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductsApi::class.java)

    var repository = AppRepositoryImpl(api, dao)

    fun setResponse(fileName: String) {
        val input = this.javaClass.classLoader?.getResourceAsStream(fileName)
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(input?.bufferedReader().use { it!!.readText() })
        )
    }

    fun setErrorResponse() {
        mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody("{}"))
    }
}
