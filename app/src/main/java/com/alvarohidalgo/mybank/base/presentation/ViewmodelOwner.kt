package com.alvarohidalgo.mybank.base.presentation


interface ViewmodelOwner<V : BaseViewModel> {
    val viewmodel: V

}