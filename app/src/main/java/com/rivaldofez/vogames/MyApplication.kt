package com.rivaldofez.vogames

import android.app.Application
import com.rivaldofez.vogames.core.di.databaseModule
import com.rivaldofez.vogames.core.di.networkModule
import com.rivaldofez.vogames.core.di.repositoryModule
import com.rivaldofez.vogames.di.useCaseModule
import com.rivaldofez.vogames.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}