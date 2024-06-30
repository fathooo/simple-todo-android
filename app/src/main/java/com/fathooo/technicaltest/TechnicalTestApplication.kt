package com.fathooo.technicaltest

import android.app.Application
import com.fathooo.technicaltest.di.apiModule
import com.fathooo.technicaltest.di.repositoryModule
import com.fathooo.technicaltest.di.useCaseModule
import com.fathooo.technicaltest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TechnicalTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TechnicalTestApplication)
            modules(listOf(apiModule, repositoryModule, useCaseModule, viewModelModule))
        }
    }
}