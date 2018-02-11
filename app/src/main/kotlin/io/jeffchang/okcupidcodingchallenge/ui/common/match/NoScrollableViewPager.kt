package io.jeffchang.okcupidcodingchallenge.ui.common.match

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.support.v4.view.ViewPager
import android.util.AttributeSet

/**
 * Disable touch events
 *
 * @property[isDisabled] Boolean that toggles whether or not the ViewPager is disabled or not.
 *
 * [Code copied from StackOverflow](https://stackoverflow.com/a/44572547/5314716)
 */

class NoScrollableViewPager constructor(context: Context,
                                        attrs: AttributeSet) : ViewPager(context, attrs) {
    var isDisabled: Boolean = false

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (isDisabled) false
        else super.onInterceptTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (isDisabled) false
        else super.onTouchEvent(event)
    }
}
