package com.test.app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.app.data.db.entity.CartProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM CartProduct")
    fun products(): Flow<List<CartProduct?>>

    @Query("SELECT count(*) FROM CartProduct")
    fun entryCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(cartProduct: CartProduct)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProducts(cartProducts: List<CartProduct?>)

}