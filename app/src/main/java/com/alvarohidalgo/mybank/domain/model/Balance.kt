package com.alvarohidalgo.mybank.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Balance(
    val value: Double,
    val currency: String
): Parcelable