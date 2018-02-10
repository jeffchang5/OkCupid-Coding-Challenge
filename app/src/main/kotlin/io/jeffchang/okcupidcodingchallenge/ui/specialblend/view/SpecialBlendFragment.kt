package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import io.jeffchang.okcupidcodingchallenge.ui.common.internet.MatchListFragment
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchRecyclerViewAdapter
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchSpaceDecoration
import io.jeffchang.okcupidcodingchallenge.ui.main.MainActivity
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.SpecialBlendPresenter
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * Created by jeffreychang on 2/8/18.
 */

class SpecialBlendFragment: MatchListFragment(), SpecialBlendView {

    @Inject lateinit var specialBlendPresenter: SpecialBlendPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCircularProgressBar("Loading Your Matches")
        specialBlendPresenter.onViewCreated()
    }

    override fun onGetMatchesSuccess(matches: List<Match>) {
        (activity as MainActivity).disableViewPager(false)
        loadMainContent()
        recyclerView.adapter = MatchRecyclerViewAdapter(context!!, matches)
    }

    override fun onGetMatchesFailure(throwable: Throwable) {
        (activity as MainActivity).disableViewPager(true)
        when (throwable) {
            is UnknownHostException -> loadNoInternet({
                specialBlendPresenter.onViewCreated()
            }, null)
            else -> loadUnknownReason({
                specialBlendPresenter.onViewCreated()
            }, null)
        }
    }

    companion object {

        const val NUMBER_OF_COLUMNS = 2

        fun newInstance(): SpecialBlendFragment {
            val fragment = SpecialBlendFragment()
            return fragment
        }
    }
}