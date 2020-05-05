package com.alvarohidalgo.mybank

import com.alvarohidalgo.mybank.presentation.login.PasswordValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PasswordValidatorUnitTest {

    val passwordValidator = PasswordValidator()

    @Test
    fun alphanumericalPasswordShouldReturnFalse() {
        val password = "12f4"
        assertFalse(passwordValidator.isPasswordValid(password))
    }

    @Test
    fun numericalPasswordShouldReturnTrue() {
        val password = "1234"
        assertTrue(passwordValidator.isPasswordValid(password))
    }

    @Test
    fun numericalPasswordWithLessThan4DigitsShouldReturnFalse() {
        val password = "124"
        assertFalse(passwordValidator.isPasswordValid(password))
    }

}
