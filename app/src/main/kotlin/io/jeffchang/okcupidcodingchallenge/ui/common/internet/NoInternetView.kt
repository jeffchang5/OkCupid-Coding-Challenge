package io.jeffchang.okcupidcodingchallenge.ui.common.internet

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.widget.RelativeLayout
import io.jeffchang.okcupidcodingchallenge.R
import kotlinx.android.synthetic.main.view_no_internet.view.*

/**
 * View that displays a screen when the user has lost connectivity.
 *
 * @property[tryAgainCallback] A callback to load to reattempt the network call.
 */

class NoInternetView: RelativeLayout {

    var tryAgainCallback: (() -> Unit)? = null
        set(callback) {
            textview_try_again_no_internet.setOnClickListener({
                callback?.invoke()
            })
        }
    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.view_no_internet, this)
        val tryAgainSpan = SpannableString(textview_try_again_no_internet.text)
        tryAgainSpan.setSpan(UnderlineSpan(), 0 , tryAgainSpan.length, 0)
        textview_try_again_no_internet.text = tryAgainSpan
    }
}