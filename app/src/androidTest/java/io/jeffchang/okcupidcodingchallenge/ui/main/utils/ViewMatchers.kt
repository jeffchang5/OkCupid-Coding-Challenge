package io.jeffchang.okcupidcodingchallenge.ui.main.utils

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.annotation.ColorInt
import android.support.constraint.ConstraintLayout
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.internal.util.Checks
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


/**
 * Utils for instrumentation testing.
 */

object ViewMatcherUtil {

    fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            internal var currentIndex = 0

            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }

}