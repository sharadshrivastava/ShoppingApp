package com.test.app.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.common.employees
import com.test.app.common.readJson
import com.test.app.data.network.Resource
import com.test.app.domain.usecases.ProductsUseCase
import com.test.app.ui.products.ProductsListViewModel
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
class EmployeesListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var productsUseCase: ProductsUseCase

    private lateinit var productsViewModel: ProductsListViewModel

    var employees = employees(readJson("response.json")) //Reading mock json

    @Before
    fun setup() {
        productsViewModel = ProductsListViewModel(productsUseCase)
    }

    @Test
    fun testEmployees() {
        runBlocking {
            Mockito.`when`(productsUseCase.products())
                .thenReturn(Resource.success(employees))
            Mockito.`when`(productsUseCase.isDBEmpty()).thenReturn(true)

            productsViewModel.products.observeForever {
                if (it.status == Resource.Status.SUCCESS) {
                    Assert.assertEquals(employees, it.data)
                }
            }
        }
    }
}