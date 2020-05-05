package com.alvarohidalgo.mybank.presentation.globalposition

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvarohidalgo.mybank.base.presentation.BaseViewModel
import com.alvarohidalgo.mybank.base.addDisposableTo
import com.alvarohidalgo.mybank.domain.model.GlobalPosition
import com.alvarohidalgo.mybank.domain.usecases.GetGlobalPositionUseCase
import io.reactivex.rxkotlin.subscribeBy

class GlobalPositionViewModel(
    private val getGlobalPositionUseCase: GetGlobalPositionUseCase
) : BaseViewModel() {

    private val _globalPositionLiveData: MutableLiveData<GlobalPosition> = MutableLiveData()
    val globalPositionLiveData: LiveData<GlobalPosition> = _globalPositionLiveData


    fun onCreate() {
        getGlobalPosition()
    }

    private fun getGlobalPosition() {
        getGlobalPositionUseCase.execute()
            .subscribeBy(
                onSuccess = {
                    _globalPositionLiveData.value = it
                },
                onError = {
                    Log.d("Global", "Error")
                }).addDisposableTo(disposeBag)
    }


}