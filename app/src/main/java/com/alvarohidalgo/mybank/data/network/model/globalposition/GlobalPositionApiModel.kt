package com.alvarohidalgo.mybank.data.network.model.globalposition

import com.alvarohidalgo.mybank.domain.model.GlobalPosition
import com.google.gson.annotations.SerializedName

data class GlobalPositionDataModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("accounts")
    val accounts: List<AccountApiModel>,
    @SerializedName("cards")
    val cards: List<CardApiModel>
) {

    fun toDomain(): GlobalPosition {
        return GlobalPosition(
            name = name,
            products = accounts.map { it.toDomain() } + cards.map { it.toDomain() }
        )

    }
}








