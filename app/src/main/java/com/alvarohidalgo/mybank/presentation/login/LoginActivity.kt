package com.alvarohidalgo.mybank.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.alvarohidalgo.mybank.R
import com.alvarohidalgo.mybank.base.presentation.ViewmodelOwner
import com.alvarohidalgo.mybank.presentation.globalposition.GlobalPositionActivity
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.checkedChanges
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(),
    ViewmodelOwner<LoginViewModel> {

    override val viewmodel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewmodel.onDniChanged(etDni.textChanges())
        viewmodel.onPasswordTextChanged(etPassword.textChanges())

        viewmodel.onLoginButtonClicked(
            loginButton.clicks()
        )
        viewmodel.onNetworkSwitchChanged(
            networkAvailableSwitch.checkedChanges()
        )

        viewmodel.dniErrorLiveData.observe(this, Observer { dniError ->
            tilDni.error = dniError
        })

        viewmodel.passwordErrorLiveData.observe(this, Observer { passwordError ->
            tilPassword.error = passwordError
        })

        viewmodel.navigateToGlobalPositionLiveData.observe(this, Observer {
            val i = Intent(this, GlobalPositionActivity::class.java)
            startActivity(i)
        })

        viewmodel.snackBarActionLiveData.observe(this, Observer {
            Snackbar.make(container, it, Snackbar.LENGTH_SHORT).show()
        })
    }
}
