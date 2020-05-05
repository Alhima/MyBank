package com.alvarohidalgo.mybank.data.network

import com.alvarohidalgo.mybank.data.local.UserLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val tokenDataSource: UserLocalDataSource) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader(
                    "tokenCredential",
                    tokenDataSource.userToken.orEmpty()
                ).build()
        )
}