package com.test.app.data.network

import com.test.app.domain.model.ApiResponse
import retrofit2.http.GET

interface ProductsApi {

    @GET(PRODUCTS_PATH)
    suspend fun products(): ApiResponse?

    companion object {
        const val BASE_URL = "https://s3.amazonaws.com/"
        private const val PRODUCTS_PATH = "/products.json"
    }
}