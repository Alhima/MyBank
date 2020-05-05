package com.alvarohidalgo.mybank.presentation.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvarohidalgo.mybank.R
import com.alvarohidalgo.mybank.base.presentation.BaseViewModel
import com.alvarohidalgo.mybank.base.resources.ResourceProvider

class InfoViewModel(
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    private val _infoUrlLiveData: MutableLiveData<String> = MutableLiveData()
    val infoUrlLiveData: LiveData<String> = _infoUrlLiveData

    fun onCreate() {
        _infoUrlLiveData.postValue(resourceProvider.getString(R.string.url_github))
    }

}