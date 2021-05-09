package com.test.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.app.data.db.entity.CartProduct

@Database(entities = [CartProduct::class], version = 1)
abstract class ProductsDB : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}