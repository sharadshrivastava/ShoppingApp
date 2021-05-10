package com.test.app.domain.usecases

import com.test.app.data.AppRepositoryImpl
import com.test.app.data.db.entity.CartProduct
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataUseCase @Inject constructor(private val repository: AppRepositoryImpl) {

    fun entryCount() = repository.entryCount()

    fun totalPrice() = repository.totalPrice()

    suspend fun insertProduct(cartProduct: CartProduct) {
        repository.insertProduct(cartProduct)
    }
}