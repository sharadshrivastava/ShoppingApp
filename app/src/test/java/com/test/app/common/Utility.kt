package com.test.app.common

import com.google.gson.Gson
import com.test.app.BaseTest
import com.test.app.data.db.entity.CartProduct
import com.test.app.domain.model.ApiResponse
import java.io.InputStreamReader

fun readJson(fileName: String): ApiResponse {
    val input = BaseTest::class.java.classLoader?.getResourceAsStream(fileName)
    val reader = InputStreamReader(input)
    return Gson().fromJson(reader, ApiResponse::class.java)
}

fun employees(apiResponse: ApiResponse): List<CartProduct> {
    val list = mutableListOf<CartProduct>()
//    apiResponse.products?.forEach {
//        list.add(it?.toProduct()!!)
//    }
    return  list.toList()
}