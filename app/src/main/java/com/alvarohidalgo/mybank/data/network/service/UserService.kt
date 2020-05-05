package com.alvarohidalgo.mybank.data.network.service

import com.alvarohidalgo.mybank.data.network.model.globalposition.GlobalPositionDataModel
import com.alvarohidalgo.mybank.data.network.model.login.LoginBody
import com.alvarohidalgo.mybank.data.network.model.login.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("/login")
    fun login(@Body loginBody: LoginBody): Single<LoginResponse>

    @GET("globalposition")
    fun getGlobalPosition(): Single<GlobalPositionDataModel>
}