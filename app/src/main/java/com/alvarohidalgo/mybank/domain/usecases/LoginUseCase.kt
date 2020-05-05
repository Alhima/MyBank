package com.alvarohidalgo.mybank.domain.usecases

import com.alvarohidalgo.mybank.base.threading.SchedulerProvider
import com.alvarohidalgo.mybank.base.applyScheduler
import com.alvarohidalgo.mybank.domain.repository.UserRepository
import io.reactivex.Single

interface LoginUserUseCase {
    fun execute(user: String, password: String): Single<String>
}

class LoginUser(
    private val userRepository: UserRepository,
    private val threadScheduler: SchedulerProvider
) : LoginUserUseCase {
    override fun execute(user: String, password: String): Single<String> {
        return userRepository.login(
            user = user,
            password = password
        ).applyScheduler(scheduler = threadScheduler)
    }
}




