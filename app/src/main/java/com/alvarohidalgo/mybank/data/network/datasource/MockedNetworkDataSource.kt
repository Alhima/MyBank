package com.alvarohidalgo.mybank.data.network.datasource

import android.app.Application
import android.content.Context
import androidx.annotation.RawRes
import com.alvarohidalgo.mybank.R
import com.alvarohidalgo.mybank.data.network.model.globalposition.GlobalPositionDataModel
import com.alvarohidalgo.mybank.data.network.model.login.LoginResponse
import com.alvarohidalgo.mybank.domain.model.GlobalPosition
import com.google.gson.Gson
import io.reactivex.Single

class MockedNetworkDataSource(private val appContext: Application) {
    fun getGlobalPosition(): Single<GlobalPosition> {
        return Single.just(appContext.jsonToClass<GlobalPositionDataModel>(R.raw.globalposition).toDomain())
    }

    fun login(): Single<String> {
        return Single.just(appContext.jsonToClass<LoginResponse>(R.raw.login).tokenCredential)
    }
}

private inline fun <reified T> Context.jsonToClass(@RawRes resourceId: Int): T =
    Gson().fromJson(
        resources.openRawResource(resourceId).bufferedReader().use { it.readText() },
        T::class.java
    )