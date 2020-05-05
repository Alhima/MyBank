package com.alvarohidalgo.mybank.presentation.login

class PasswordValidator {

    fun isPasswordValid(password: String): Boolean {
        return password.all { it.isDigit() } && password.length == 4
    }
}
