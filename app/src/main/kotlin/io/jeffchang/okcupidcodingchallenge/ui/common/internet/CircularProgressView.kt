package io.jeffchang.okcupidcodingchallenge.ui.common.internet

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import io.jeffchang.okcupidcodingchallenge.R
import kotlinx.android.synthetic.main.view_circular_progress.view.*

/**
 * View that displays a progress bar and a message that is the rationale for the progress bar.
 */

class CircularProgressView : RelativeLayout {

    var loadingText: String? = null
        set(text) {
            circular_progress_textview_loading.text = text
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
        inflate(context, R.layout.view_circular_progress, this)
    }
}