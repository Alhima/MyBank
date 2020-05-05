package com.alvarohidalgo.mybank.data.network.model.globalposition

import com.alvarohidalgo.mybank.domain.model.Product
import com.google.gson.annotations.SerializedName


data class CardApiModel(
    @SerializedName("pan")
    val pan: String,
    @SerializedName("alias")
    val alias: String,
    @SerializedName("avalaible")
    val avalaible: BalanceApiModel
) {

    fun toDomain(): Product.Card {
        return Product.Card(
            pan = pan,
            alias = alias,
            avalaible = avalaible.toDomain()
        )
    }
}