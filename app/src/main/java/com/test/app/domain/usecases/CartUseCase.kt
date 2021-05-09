package com.test.app.domain.usecases

import com.test.app.data.AppRepositoryImpl
import javax.inject.Inject

class CartUseCase @Inject constructor(private val repository: AppRepositoryImpl) {

    fun cartProducts() = repository.cartProducts()

}