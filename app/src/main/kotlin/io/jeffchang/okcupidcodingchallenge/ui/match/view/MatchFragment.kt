package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import android.os.Bundle
import android.view.View
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.ui.common.internet.MatchListFragment

/**
 * Created by jeffreychang on 2/8/18.
 */

class MatchFragment : MatchListFragment(), MatchView {

    companion object {
        fun newInstance(): MatchFragment {
            val fragment = MatchFragment()
            return fragment
        }
    }
}