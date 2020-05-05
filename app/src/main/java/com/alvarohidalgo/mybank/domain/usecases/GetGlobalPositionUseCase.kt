package com.alvarohidalgo.mybank.domain.usecases

import com.alvarohidalgo.mybank.base.threading.SchedulerProvider
import com.alvarohidalgo.mybank.base.applyScheduler
import com.alvarohidalgo.mybank.domain.model.GlobalPosition
import com.alvarohidalgo.mybank.domain.repository.UserRepository
import io.reactivex.Single

interface GetGlobalPositionUseCase {
    fun execute(): Single<GlobalPosition>
}

class GetGlobalPosition(
    private val userRepository: UserRepository,
    private val threadScheduler: SchedulerProvider
) : GetGlobalPositionUseCase {
    override fun execute(): Single<GlobalPosition> {
        return userRepository.getGlobalposition()
            .applyScheduler(scheduler = threadScheduler)
    }
}