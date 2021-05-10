package com.test.app.common

import com.google.gson.Gson
import com.test.app.BaseTest
import com.test.app.domain.model.ApiResponse
import com.test.app.domain.model.Product
import java.io.InputStreamReader

fun readJson(fileName: String): ApiResponse {
    val input = BaseTest::class.java.classLoader?.getResourceAsStream(fileName)
    val reader = InputStreamReader(input)
    return Gson().fromJson(reader, ApiResponse::class.java)
}

fun products(): List<Product?>? = readJson("response.json").products
