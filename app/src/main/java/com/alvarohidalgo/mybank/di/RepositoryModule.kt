package com.alvarohidalgo.mybank.di

import com.alvarohidalgo.mybank.data.repository.UserRepositoryImpl
import com.alvarohidalgo.mybank.domain.repository.UserRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get(), get(), get(), get()) }
}