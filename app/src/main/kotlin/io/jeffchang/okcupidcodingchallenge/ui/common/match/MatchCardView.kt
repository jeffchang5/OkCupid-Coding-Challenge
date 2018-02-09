package io.jeffchang.okcupidcodingchallenge.ui.common.match

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import io.jeffchang.okcupidcodingchallenge.R

/**
 * Created by jeffreychang on 2/9/18.
 */

class MatchCardView(context: Context): CardView(context) {

    init {
        View.inflate(context, R.layout.view_match_card, this)
    }
}