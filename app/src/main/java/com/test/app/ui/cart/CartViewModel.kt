package com.test.app.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.test.app.domain.usecases.CartUseCase
import com.test.app.domain.usecases.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCase: CartUseCase,
    private val dataUseCase: DataUseCase
) : ViewModel() {

    var totalPrice = dataUseCase.totalPrice()?.asLiveData()

    var cartProducts = useCase.cartProducts()?.asLiveData() //converting it to liveData as it has to be observed by data binding.
}