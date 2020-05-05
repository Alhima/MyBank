package com.alvarohidalgo.mybank

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.alvarohidalgo.mybank.presentation.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun dniErrorIsShownWhenDniIsNotCorrect() {
        onView(withId(R.id.etDni)).perform(typeText("70909113k"))

        onView(
            withText(activityRule.activity.getString(R.string.error_formating_dni)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun networkCheckerIsSetTotTrueByDefault() {
        onView(withId(R.id.networkAvailableSwitch)).check(matches(isChecked()))
    }
}


