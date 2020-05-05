package com.alvarohidalgo.mybank.base.resources

import android.app.Application

class AndroidResourceProvider(val app: Application): ResourceProvider {
    override fun getString(resId: Int): String {
        return app.getString(resId)
    }
}