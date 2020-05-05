package com.alvarohidalgo.mybank.data.network.datasource

import com.alvarohidalgo.mybank.data.network.model.login.LoginBody
import com.alvarohidalgo.mybank.data.network.service.UserService
import com.alvarohidalgo.mybank.domain.model.GlobalPosition
import io.reactivex.Single

class UserNetworkDataSource(
    private val userService: UserService
) {

    fun getGlobalPostion(): Single<GlobalPosition> {
        return userService.getGlobalPosition().map { it.toDomain() }
    }

    fun login(user: String, password: String): Single<String> {
        return userService.login(LoginBody(user, password)).map {
            it.tokenCredential
        }
    }
}