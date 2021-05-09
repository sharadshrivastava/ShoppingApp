package com.test.app.domain.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @field:SerializedName("products")
    val products: List<Product?>? = null
)
