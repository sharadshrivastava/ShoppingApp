package com.test.app.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.test.app.domain.usecases.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCase: CartUseCase
) : ViewModel() {

    var totalPrice = MutableLiveData(0.0)

    var cartProducts = useCase.cartProducts()?.asLiveData()

    init {
        viewModelScope.launch {
            totalPrice()
        }
    }

    private suspend fun totalPrice() {
        useCase.cartProducts()?.collect {
            var total = 0.0
            it.forEach {
                val price = if (it?.discountPrice != -1) it?.discountPrice else it.price
                total += price?:0
            }
            totalPrice.value = total
        }
    }
}