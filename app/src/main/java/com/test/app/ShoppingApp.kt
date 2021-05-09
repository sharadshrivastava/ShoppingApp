package com.test.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoppingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: ShoppingApp? = null
        fun get(): ShoppingApp? = instance
    }
}