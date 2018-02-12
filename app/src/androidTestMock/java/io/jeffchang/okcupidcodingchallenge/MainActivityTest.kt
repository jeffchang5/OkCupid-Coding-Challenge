
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchCardView
import io.jeffchang.okcupidcodingchallenge.ui.main.MainActivity
import io.jeffchang.okcupidcodingchallenge.ui.main.utils.ViewMatcherUtil.withIndex
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Tests the logic within the MainActivity.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun beforeMainActivity() {
        val activity = activityRule.activity
        activity.runOnUiThread {
            activity.clearAllMatches()
        }
    }

    /**
     * Tests if card can be clicked.
     */
    @Test
    fun testIfCanClickCard() {
        val recyclerView = onView(
                withIndex(withClassName(`is`(MatchCardView::class.java.canonicalName)),
                        0))
        recyclerView.perform(click())
    }

    /**
     * Tests if the card exists.
     */
    @Test fun testIfCardExists() {
        val viewGroup = onView(withIndex(withId(R.id.match_card_text_portion_layout), 0))
        viewGroup.check(matches(isDisplayed()))
    }

    /**
     * Tests if card appears on the next viewpager
     */
    @Test fun testIfCardUpdates() {
        val viewPager = onView(withIndex(withId(R.id.activity_main_viewpager), 0))
        viewPager.perform(swipeLeft())
        val matchCard =
                onView(allOf(withId(R.id.match_card_text_portion_layout), isDisplayed()))
        matchCard.check(matches(isDisplayed()))
    }
}
