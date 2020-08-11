package br.com.lucaspires.challengexp.iu.fragment

import android.content.Intent
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.iu.activity.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CharactersFragmentTest{

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setup() {
        mActivityRule.launchActivity(Intent())
    }

    @Test
    fun checkViewsTexts() {

        onView(withId(R.id.et_filter_name)).check(matches(isDisplayed()))

        onView(withId(R.id.swipe_refresh)).check(matches(isDisplayed()))

        onView(allOf(
                instanceOf(TextView::class.java),
                withParent(withResourceName("action_bar"))))
            .check(matches(withText("Characters")))

        onView(withId(R.id.progress_loading)).check(matches(isDisplayed()))

        onView(withId(R.id.vp_container)).perform(swipeLeft())

        onView(allOf(
            instanceOf(TextView::class.java),
            withParent(withResourceName("action_bar"))))
            .check(matches(withText("Favorites")))

        onView(withId(R.id.vp_container)).perform(swipeRight())

        onView(allOf(
            instanceOf(TextView::class.java),
            withParent(withResourceName("action_bar"))))
            .check(matches(withText("Characters")))
    }
}