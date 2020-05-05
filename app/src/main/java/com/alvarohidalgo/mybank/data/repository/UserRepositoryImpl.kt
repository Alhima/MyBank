package com.alvarohidalgo.mybank.data.repository

import com.alvarohidalgo.mybank.data.local.UserLocalDataSource
import com.alvarohidalgo.mybank.data.network.NetworkAvailabilityResource
import com.alvarohidalgo.mybank.data.network.datasource.MockedNetworkDataSource
import com.alvarohidalgo.mybank.data.network.datasource.UserNetworkDataSource
import com.alvarohidalgo.mybank.domain.model.GlobalPosition
import com.alvarohidalgo.mybank.domain.repository.UserRepository
import io.reactivex.Single

class UserRepositoryImpl(
    private val networkAvailabilityResource: NetworkAvailabilityResource,
    private val networkDataSource: UserNetworkDataSource,
    private val mockedNetWorkDataSource: MockedNetworkDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override fun login(user: String, password: String): Single<String> {
        return if (networkAvailabilityResource.isNetworkAvailable) {
            networkDataSource.login(user, password)
                .doOnSuccess { token ->
                    localDataSource.userToken = token
                }
        } else {
            mockedNetWorkDataSource.login()
        }

    }

    override fun getGlobalposition(): Single<GlobalPosition> {
        return if (networkAvailabilityResource.isNetworkAvailable) {
            networkDataSource.getGlobalPostion()
        } else {
            mockedNetWorkDataSource.getGlobalPosition()
        }

    }

}