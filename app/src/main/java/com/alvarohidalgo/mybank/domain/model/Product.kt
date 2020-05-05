package com.alvarohidalgo.mybank.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Product {
    @Parcelize
    data class Account(val iban: String, val alias: String, val balance: Balance) : Product(), Parcelable
    @Parcelize
    data class Card(val pan: String, val alias: String, val avalaible: Balance) : Product(), Parcelable
}