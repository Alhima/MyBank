package com.alvarohidalgo.mybank

import android.app.Application
import com.alvarohidalgo.mybank.di.*
import org.koin.android.ext.android.startKoin

class MyBankApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(
                threadingModule,
                presentationModule,
                domainModule,
                repositoryModule,
                networkModule,
                localDataModule
            )
        )
    }
}