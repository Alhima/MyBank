package com.alvarohidalgo.mybank.base

import android.annotation.SuppressLint
import com.alvarohidalgo.mybank.base.threading.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

fun <T : Any> Single<T>.applyScheduler(scheduler: SchedulerProvider): Single<T> {
    return subscribeOn(
        scheduler.io()
    ).observeOn(
        scheduler.main()
    )
}

fun Disposable.addDisposableTo(disposebag: CompositeDisposable): Disposable = apply {
    addTo(disposebag)
}


@SuppressLint("CheckResult")
fun <T> Observable<in T>.subscribeWithThrottle(action: () -> T): Disposable {
    return this.throttleFirst(500, TimeUnit.MILLISECONDS).subscribe { action() }
}