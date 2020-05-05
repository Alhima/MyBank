package com.alvarohidalgo.mybank.base.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val disposeBag: CompositeDisposable = CompositeDisposable()
}