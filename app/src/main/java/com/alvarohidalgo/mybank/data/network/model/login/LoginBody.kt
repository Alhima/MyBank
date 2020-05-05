package com.alvarohidalgo.mybank.data.network.model.login

import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("user")
    val user: String,
    @SerializedName("password")
    val password: String
)