package com.alvarohidalgo.mybank.domain.usecases

import com.alvarohidalgo.mybank.data.network.NetworkAvailabilityResource

interface SetNetworkAvailabilityUseCase {
    fun execute(available: Boolean)
}

class SetNetworkAvailability(private val networkAvailabilityResource: NetworkAvailabilityResource) : SetNetworkAvailabilityUseCase {

    override fun execute(available: Boolean) {
        networkAvailabilityResource.isNetworkAvailable = available
    }

}