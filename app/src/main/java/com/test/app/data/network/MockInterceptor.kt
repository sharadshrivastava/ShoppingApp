package com.test.app.data.network

import com.test.app.R
import com.test.app.ShoppingApp
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody

// This class is just to support offline JSON with Retrofit for testing.
// In real app this class won't be required.
class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = data()!!
        return Response.Builder().code(200).message(response).request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(response.toResponseBody())
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun data() = ShoppingApp.get()?.resources?.openRawResource(R.raw.response)
        ?.bufferedReader().use { it?.readText() }
}