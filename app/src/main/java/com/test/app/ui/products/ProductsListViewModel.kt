package com.test.app.ui.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.test.app.data.db.entity.CartProduct
import com.test.app.data.network.Resource
import com.test.app.domain.usecases.DataUseCase
import com.test.app.domain.usecases.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val productUseCase: ProductsUseCase,
    private val dataUseCase: DataUseCase
) : ViewModel() {

    private val productsLiveData = MutableLiveData(Unit)

    //using switchMap to avoid observing two live data for refresh functionality in Data-Binding scenarios.
    var products = productsLiveData.switchMap {
        products()
    }

    fun refresh() {
        productsLiveData.value = Unit
    }

    private fun products() = liveData {
        if (entryCount()?.first() == 0) emit(Resource.loading())
        emit(productUseCase.products())
    }

    fun entryCount() = dataUseCase.entryCount()

    suspend fun insertProduct(cartProduct: CartProduct) {
        dataUseCase.insertProduct(cartProduct)
    }
}