package com.example.kotlin1lesson6

import android.app.Application
import com.example.kotlin1lesson6.servicelocator.networkModule
import com.example.kotlin1lesson6.servicelocator.repositoriesModel
import com.example.kotlin1lesson6.servicelocator.viewModelsModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoriesModel, viewModelsModule)
        }
    }
}