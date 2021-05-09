package com.test.app.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.app.data.db.entity.CartProduct
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @field:SerializedName("discount_price")
    val discountPrice: Int? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("product_name")
    val productName: String? = null,

    @field:SerializedName("img_url")
    val imgUrl: String? = null
) : Parcelable {

    fun toCartProduct() = CartProduct(productName, imgUrl, description, price, discountPrice)
}