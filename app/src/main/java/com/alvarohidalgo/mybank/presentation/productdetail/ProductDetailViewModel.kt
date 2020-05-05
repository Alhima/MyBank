package com.alvarohidalgo.mybank.presentation.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvarohidalgo.mybank.base.presentation.BaseViewModel
import com.alvarohidalgo.mybank.domain.model.Product

class ProductDetailViewModel : BaseViewModel() {

    private val _cardLiveData: MutableLiveData<Product.Card> = MutableLiveData()
    val cardLiveData: LiveData<Product.Card> = _cardLiveData

    private val _accounttLiveData: MutableLiveData<Product.Account> = MutableLiveData()
    val accounttLiveData: LiveData<Product.Account> = _accounttLiveData


    fun setProduct(product: Product?) {
        product?.let {
            if (it is Product.Card) {
                _cardLiveData.postValue(it)
            }
            if (it is Product.Account) {
                _accounttLiveData.postValue(it)
            }
        }
    }
}