package com.test.app.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.common.products
import com.test.app.data.network.Resource
import com.test.app.domain.usecases.DataUseCase
import com.test.app.domain.usecases.ProductsUseCase
import com.test.app.ui.products.ProductsListViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductsListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var productsUseCase: ProductsUseCase

    @Mock
    private lateinit var dataUseCase: DataUseCase

    private lateinit var productsViewModel: ProductsListViewModel

    var employees = products() //Reading mock json

    @Before
    fun setup() {
        productsViewModel = ProductsListViewModel(productsUseCase, dataUseCase)
    }

    @Test
    fun testEmployees() {
        runBlocking {
            Mockito.`when`(productsUseCase.products())
                .thenReturn(Resource.success(employees))
            Mockito.`when`(dataUseCase.entryCount()).thenReturn(flowOf(5))

            productsViewModel.products.observeForever {
                if (it.status == Resource.Status.SUCCESS) {
                    Assert.assertEquals(employees, it.data)
                }
            }
        }
    }
}

// Not writing unit test cases for CartViewModel as that gonna be as same as this one.
// In this test app, intention is show the way of writing unit testcases rather than having full coder coverage.