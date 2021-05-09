package com.test.app.domain

import com.test.app.data.db.entity.CartProduct
import com.test.app.data.network.Resource
import com.test.app.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun products(): Resource<List<Product?>?>

    fun cartProducts(): Flow<List<CartProduct?>>?

    fun entryCount(): Flow<Int>?

    suspend fun insertProduct(cartProduct: CartProduct)
}