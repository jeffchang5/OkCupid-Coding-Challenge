package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import android.os.Bundle
import android.view.View
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.ui.common.internet.InternetFragment
import timber.log.Timber

/**
 * Created by jeffreychang on 2/8/18.
 */

class SpecialBlendFragment: InternetFragment(), MatchView {

    override var layoutResourceID: Int = R.layout.fragment_special_blend

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("Special Blend")
    }
    companion object {
        fun newInstance(): SpecialBlendFragment {
            val fragment = SpecialBlendFragment()
            return fragment
        }
    }
}