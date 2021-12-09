package com.example.fakenews

import android.app.Application
import com.example.fakenews.data.di.dataModule
import com.example.fakenews.domain.di.domainModule
import com.example.fakenews.presentation.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RoomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@RoomApplication)
            modules(
                dataModule,
                domainModule,
                viewModelsModule
            )
        }
    }
}