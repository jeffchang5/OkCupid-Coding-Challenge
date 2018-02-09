package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import android.os.Bundle
import android.view.View
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.ui.common.internet.InternetFragment
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_special_blend.*
import android.support.v7.widget.GridLayoutManager
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchSpaceDecoration


/**
 * Created by jeffreychang on 2/8/18.
 */

class SpecialBlendFragment: InternetFragment(), MatchView {

    override var layoutResourceID: Int = R.layout.fragment_special_blend

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        special_blend_recycler_view.layoutManager = GridLayoutManager(context, 2)
        special_blend_recycler_view
                .addItemDecoration(MatchSpaceDecoration(context!!,8))
        special_blend_recycler_view.adapter =
                MatchRecyclerViewAdapter(context!!, null)
    }
    companion object {
        fun newInstance(): SpecialBlendFragment {
            val fragment = SpecialBlendFragment()
            return fragment
        }
    }
}