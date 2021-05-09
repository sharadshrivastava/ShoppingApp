package com.test.app.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * In this test app, Product and CartProduct Entity is same but still keeping
 * them as separate classes because in real apps entities and DTOs are generally different and
 * keeping them separate gives scalability and loose coupling.
 */
@Entity
data class CartProduct(
    @ColumnInfo(name = "product_name") val productName: String?,
    @ColumnInfo(name = "img_url") val imgUrl: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "price") val price: Int?,
    @ColumnInfo(name = "discount_price") val discountPrice: Int?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)