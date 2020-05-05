package com.alvarohidalgo.mybank.di

import com.alvarohidalgo.mybank.data.network.ApiConfig
import com.alvarohidalgo.mybank.data.network.NetworkAvailabilityResource
import com.alvarohidalgo.mybank.data.network.TokenInterceptor
import com.alvarohidalgo.mybank.data.network.datasource.MockedNetworkDataSource
import com.alvarohidalgo.mybank.data.network.datasource.UserNetworkDataSource
import com.alvarohidalgo.mybank.data.network.service.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    factory { TokenInterceptor(get()) }
    factory { HttpLoggingInterceptor() }

    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<TokenInterceptor>())
            .build()
    }

    factory<Retrofit> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl(ApiConfig.API_URL)
            .build()
    }

    factory<UserService> { get<Retrofit>().create(UserService::class.java) }

    factory { UserNetworkDataSource(get()) }
    factory { MockedNetworkDataSource(androidApplication()) }

    single<NetworkAvailabilityResource> {
        object : NetworkAvailabilityResource {
            override var isNetworkAvailable: Boolean = true
        }
    }
}