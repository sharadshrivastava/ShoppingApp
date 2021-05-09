package com.test.app.domain.usecases

import com.test.app.data.AppRepositoryImpl
import javax.inject.Inject

class ProductsUseCase @Inject constructor(private val repository: AppRepositoryImpl) {

    suspend fun products() = repository.products()
}