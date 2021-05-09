package com.test.app.db

import android.content.Context
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.app.data.db.ProductsDB
import com.test.app.data.db.ProductsDao
import com.test.app.data.db.entity.CartProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class DBTest {

    lateinit var productsDao: ProductsDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        productsDao = Room.inMemoryDatabaseBuilder(
            context, ProductsDB::class.java
        ).build().productsDao()

        runBlocking {
            val list = listOf(
                CartProduct(
                    "Nintendo Switch",
                    "",
                    "Play your favourite games anytime, anywhere, with anyone, with Nintendo Switch",
                    400,
                    -1
                )
            )
            productsDao.insertProducts(list)
        }
    }


    @Test
    fun testInsert() {
        runBlocking {
            withContext(Dispatchers.Main) {
                productsDao.products().asLiveData().observeForever {
                    Assert.assertEquals("Nintendo Switch", it[0]?.productName)
                }
            }
        }
    }

    @Test
    fun testDBEntries() {
        runBlocking {
            val count = productsDao.entryCount()
            Assert.assertTrue(count == 1)
        }
    }
}