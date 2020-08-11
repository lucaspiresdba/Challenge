package br.com.lucaspires.challengexp.iu.fragment

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.iu.activity.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class FavoriteFragmentTest {

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setup() {
        mActivityRule.launchActivity(Intent())
    }

    @Test
    fun shouldShowNoFavoriteItems() {

        onView(withId(R.id.vp_container)).perform(swipeLeft())

        onView(withId(R.id.rv_favorites)).check(matches(not(isDisplayed())))

        onView(withId(R.id.text_feedback_favorite_user)).check(matches(withText("sem favoritos")))
    }
}