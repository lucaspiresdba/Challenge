package br.com.lucaspires.challengexp.iu.activity

import android.content.Intent
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.iu.activity.CharacterDetailsActivity.Companion.CHARACTER_DATE
import br.com.lucaspires.domain.model.CharacterModel
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailsActivityTest {

    private val item = CharacterModel(1, "Espresso Test", "", "Description Test", true)

    @get:Rule
    val mActivityRule: ActivityTestRule<CharacterDetailsActivity> =
        object : ActivityTestRule<CharacterDetailsActivity>(CharacterDetailsActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, CharacterDetailsActivity::class.java).apply {
                    putExtra(
                        CHARACTER_DATE,
                        item
                    )
                }
            }
        }

    @Before
    fun setup() {
        mActivityRule.launchActivity(mActivityRule.activity.intent)
    }

    @Test
    fun checkViewsValues() {
        Espresso.onView(withId(R.id.text_description))
            .check(matches(withText(item.description)))

        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                withParent(withResourceName("action_bar"))
            )
        )
            .check(matches(withText(item.name)))
    }
}