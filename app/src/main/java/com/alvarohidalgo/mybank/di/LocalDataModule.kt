package com.alvarohidalgo.mybank.di

import com.alvarohidalgo.mybank.data.local.UserLocalDataSource
import org.koin.dsl.module.module

val localDataModule = module {

    single { UserLocalDataSource() }
}