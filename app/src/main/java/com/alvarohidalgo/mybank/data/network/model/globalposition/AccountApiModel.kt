package com.alvarohidalgo.mybank.data.network.model.globalposition

import com.alvarohidalgo.mybank.domain.model.Product
import com.google.gson.annotations.SerializedName


data class AccountApiModel(
    @SerializedName("IBAN")
    val iban: String,
    @SerializedName("alias")
    val alias: String,
    @SerializedName("balance")
    val balance: BalanceApiModel
) {

     fun toDomain(): Product.Account {
         return Product.Account(
             iban = iban,
             alias = alias,
             balance = balance.toDomain()
         )
     }
}