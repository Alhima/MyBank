package com.alvarohidalgo.mybank.base.threading

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun main(): Scheduler
}