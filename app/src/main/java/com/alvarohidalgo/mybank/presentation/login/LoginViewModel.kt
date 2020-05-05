package com.alvarohidalgo.mybank.presentation.login

import android.drm.DrmStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvarohidalgo.mybank.R
import com.alvarohidalgo.mybank.base.presentation.BaseViewModel
import com.alvarohidalgo.mybank.base.addDisposableTo
import com.alvarohidalgo.mybank.base.presentation.ActionLiveData
import com.alvarohidalgo.mybank.base.resources.ResourceProvider
import com.alvarohidalgo.mybank.base.subscribeWithThrottle
import com.alvarohidalgo.mybank.domain.usecases.LoginUserUseCase
import com.alvarohidalgo.mybank.domain.usecases.SetNetworkAvailability
import com.alvarohidalgo.mybank.domain.usecases.SetNetworkAvailabilityUseCase
import com.jakewharton.rxbinding3.InitialValueObservable
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val resourceProvider: ResourceProvider,
    private val dniValidator: DniValidator,
    private val passwordValidator: PasswordValidator,
    private val setNetworkAvailability: SetNetworkAvailabilityUseCase
) : BaseViewModel() {

    private val _dniErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    private val _passwordErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    private val _navigateToGlobalPositionLiveData: ActionLiveData<String> = ActionLiveData()
    private val _snackBarActionLiveData: ActionLiveData<String> = ActionLiveData()

    val dniErrorLiveData: LiveData<String?> = _dniErrorLiveData
    val passwordErrorLiveData: LiveData<String?> = _passwordErrorLiveData
    val navigateToGlobalPositionLiveData: LiveData<String> = _navigateToGlobalPositionLiveData
    val snackBarActionLiveData: LiveData<String> = _snackBarActionLiveData

    var dni: String = ""
    var password: String = ""


    fun onDniChanged(textChanges: InitialValueObservable<CharSequence>) {
        textChanges.subscribe {
            validateDni(it.toString())
            dni = it.toString()
        }.addDisposableTo(disposeBag)
    }

    fun onPasswordTextChanged(textChanges: InitialValueObservable<CharSequence>) {
        textChanges.subscribe {
            validatePassword(it.toString())
            password = it.toString()
        }.addDisposableTo(disposeBag)
    }

    fun onLoginButtonClicked(clicks: Observable<Unit>) {
        clicks.subscribeWithThrottle {
            login(user = dni, password = password)
        }.addDisposableTo(disposeBag)
    }


    fun onNetworkSwitchChanged(checkedChanges: Observable<Boolean>) {
        checkedChanges.subscribe {
            setNetworkAvailability.execute(it)
        }.addDisposableTo(disposeBag)
    }

    private fun validateDni(dni: String) {
        val isValidFormat = dniValidator.isDniValid(dni)
        if (dni.length == 9 && !isValidFormat) {
            _dniErrorLiveData.postValue(resourceProvider.getString(R.string.error_formating_dni))
        } else {
            _dniErrorLiveData.postValue(null)
        }
    }

    private fun validatePassword(password: String) {
        val isValidFormat = passwordValidator.isPasswordValid(password)
        if (password.length == 4 && !isValidFormat) {
            _passwordErrorLiveData.postValue(resourceProvider.getString(R.string.error_formating_password))
        } else {
            _passwordErrorLiveData.postValue(null)
        }
    }

    fun login(user: String, password: String) {
        loginUserUseCase.execute(user, password).subscribeBy(
            onSuccess = {
                _navigateToGlobalPositionLiveData.sendAction("")
            },
            onError = {
                _snackBarActionLiveData.sendAction(resourceProvider.getString(R.string.error_login))
            }
        ).addDisposableTo(disposeBag)
    }

}