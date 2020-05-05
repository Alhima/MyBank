package com.alvarohidalgo.mybank.domain.repository

import com.alvarohidalgo.mybank.domain.model.GlobalPosition
import io.reactivex.Single

interface UserRepository {
    fun login(user: String, password: String): Single<String>
    fun getGlobalposition(): Single<GlobalPosition>
}