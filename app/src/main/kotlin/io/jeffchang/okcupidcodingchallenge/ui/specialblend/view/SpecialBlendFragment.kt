package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.ui.common.internet.InternetFragment
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchRecyclerViewAdapter
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchSpaceDecoration
import io.jeffchang.okcupidcodingchallenge.ui.main.MainActivity
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.SpecialBlendPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_special_blend.*
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * Created by jeffreychang on 2/8/18.
 */

class SpecialBlendFragment: InternetFragment(), SpecialBlendView {


    override var layoutResourceID: Int = R.layout.fragment_special_blend

    @Inject lateinit var specialBlendPresenter: SpecialBlendPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCircularProgressBar("Loading Your Matches")
        specialBlendPresenter.onViewCreated()
    }

    override fun onGetMatchesSuccess(matchResponse: List<Match?>?) {
        (activity as MainActivity).disableViewPager(false)
        loadMainContent()
        special_blend_recycler_view.layoutManager = GridLayoutManager(context, 2)
        special_blend_recycler_view.adapter =
                MatchRecyclerViewAdapter(context!!, null)
        special_blend_recycler_view
                .addItemDecoration(MatchSpaceDecoration(context!!,8))
    }

    override fun onGetMatchesFailure(throwable: Throwable) {
        (activity as MainActivity).disableViewPager(true)
        when (throwable) {
            is UnknownHostException -> loadNoInternet({
                specialBlendPresenter.onViewCreated()
            }, null)
        }
    }

    companion object {
        fun newInstance(): SpecialBlendFragment {
            val fragment = SpecialBlendFragment()
            return fragment
        }
    }
}