package com.alvarohidalgo.mybank

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.alvarohidalgo.mybank.presentation.info.InfoActivity
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<InfoActivity> = ActivityTestRule(InfoActivity::class.java)

    @Test
    fun correctGithubUrlIsShowed() {
        Espresso.onView(ViewMatchers.withId(R.id.url)).check(matches(withText(containsString("https://github.com/Alhima/MyBank"))))
    }
}