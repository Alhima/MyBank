package com.alvarohidalgo.mybank.di

import com.alvarohidalgo.mybank.base.threading.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

val threadingModule = module {

    single<SchedulerProvider> {
        object : SchedulerProvider {
            override fun io() = Schedulers.io()
            override fun main() = AndroidSchedulers.mainThread()
        }
    }
}