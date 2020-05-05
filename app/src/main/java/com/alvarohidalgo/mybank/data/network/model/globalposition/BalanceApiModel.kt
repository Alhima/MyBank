package com.alvarohidalgo.mybank.data.network.model.globalposition

import com.alvarohidalgo.mybank.domain.model.Balance
import com.google.gson.annotations.SerializedName

data class BalanceApiModel(
    @SerializedName("value")
    val value: String,
    @SerializedName("currency")
    val currency: String
) {

    fun toDomain(): Balance {
        return Balance(
            value = value.toDoubleOrNull() ?: 0.0,
            currency = currency
        )
    }
}

