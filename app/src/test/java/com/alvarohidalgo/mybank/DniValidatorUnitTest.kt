package com.alvarohidalgo.mybank

import com.alvarohidalgo.mybank.presentation.login.DniValidator
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DniValidatorUnitTest {

    val dniValidator = DniValidator()

    @Test
    fun correctDniShouldReturnTrue() {
        val dni = "50289527G"
        assertTrue(dniValidator.isDniValid(dni))
    }

    @Test
    fun invalidNumbersDniShouldReturnFalse() {
        val dni = "50282527G"
        assertFalse(dniValidator.isDniValid(dni))
    }

    @Test
    fun invalidLetterDniShouldReturnFalse() {
        val dni = "50289527P"
        assertFalse(dniValidator.isDniValid(dni))
    }

    @Test
    fun notCapsLetterShouldReturnFalse() {
        val dni = "50289527g"
        assertFalse(dniValidator.isDniValid(dni))
    }
}
