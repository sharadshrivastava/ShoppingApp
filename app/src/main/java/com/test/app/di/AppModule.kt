package com.test.app.di

import android.app.Application
import androidx.room.Room
import com.test.app.data.db.ProductsDB
import com.test.app.data.db.ProductsDao
import com.test.app.data.network.ProductsApi
import com.test.app.data.network.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): ProductsApi {
        return Retrofit.Builder()
            .baseUrl(ProductsApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun client(): OkHttpClient {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(MockInterceptor()) //This is for offline data testing.
            .build()
    }

    @Provides
    @Singleton
    fun dao(app: Application): ProductsDao = Room.databaseBuilder(
        app, ProductsDB::class.java, "app-db"
    ).build().productsDao()
}