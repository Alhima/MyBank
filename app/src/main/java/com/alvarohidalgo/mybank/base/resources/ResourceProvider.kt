package com.alvarohidalgo.mybank.base.resources

interface ResourceProvider {

    fun getString(resId: Int): String
}