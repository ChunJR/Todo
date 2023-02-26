package com.example.todo.base

import android.app.Application
import com.example.todo.di.dataModule
import com.example.todo.di.networkModule
import com.example.todo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@BaseApplication)

            modules(
                listOf(
                    networkModule,
                    dataModule,
                    viewModelModule
                )
            )
        }
    }
}