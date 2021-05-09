package com.test.app.data

import com.test.app.data.db.ProductsDao
import com.test.app.data.db.entity.CartProduct
import com.test.app.data.network.ProductsApi
import com.test.app.data.network.Resource
import com.test.app.data.network.ResponseHandler
import com.test.app.domain.AppRepository
import com.test.app.domain.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val api: ProductsApi,
    private val dao: ProductsDao?
) : AppRepository {

    override suspend fun products(): Resource<List<Product?>?> = try {
        ResponseHandler.handleSuccess(api.products()?.products)
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }

    override fun cartProducts() = dao?.products()

    override fun entryCount() = dao?.entryCount()

    override suspend fun insertProduct(cartProduct: CartProduct) {
        dao?.insertProduct(cartProduct)
    }
}