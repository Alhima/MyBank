package com.alvarohidalgo.mybank.di

import com.alvarohidalgo.mybank.domain.usecases.*
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val domainModule: Module = module {

    factory<GetGlobalPositionUseCase> { GetGlobalPosition(get(), get()) }
    factory<LoginUserUseCase> { LoginUser(get(), get()) }

    factory<SetNetworkAvailabilityUseCase> { SetNetworkAvailability(get()) }
}