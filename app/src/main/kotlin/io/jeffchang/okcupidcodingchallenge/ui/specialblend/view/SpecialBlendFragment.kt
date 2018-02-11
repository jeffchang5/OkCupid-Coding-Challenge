package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import android.content.Context
import android.os.Bundle
import android.view.View
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.ui.common.internet.MatchListFragment
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchCardView
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchRecyclerViewAdapter
import io.jeffchang.okcupidcodingchallenge.ui.main.MainActivity
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.SpecialBlendPresenter
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by jeffreychang on 2/8/18.
 */

class SpecialBlendFragment
    : MatchListFragment(),
        SpecialBlendView,
        MatchCardView.OnCardClickedListener {

    @Inject lateinit var specialBlendPresenter: SpecialBlendPresenter

    private var onCardClickedListener: OnCardClickedListener? = null

    private val matchList: ArrayList<Match> = ArrayList()

    private var matchRecyclerViewAdapter: MatchRecyclerViewAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCircularProgressBar("Loading Your Matches")
        specialBlendPresenter.onViewCreated()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnCardClickedListener)
            onCardClickedListener = context
    }

    override fun onCardClicked(match: Match, isLiked: Boolean) {
        onCardClickedListener?.onFromSpecialBlendFragmentToggleLike(match, isLiked)
    }

    override fun onGetMatchesSuccess(matches: ArrayList<Match>) {
        (activity as MainActivity).disableViewPager(false)
        loadMainContent()
        this.matchList.addAll(matches)
        matchRecyclerViewAdapter =
                MatchRecyclerViewAdapter(context!!, matchList, true, this)
        recyclerView.adapter = matchRecyclerViewAdapter
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

    fun removeLikeFromMatchList(match: Match) {
        val matchIndex = matchList.indexOf(match)
        matchList[matchIndex].liked = false
        matchRecyclerViewAdapter?.notifyDataSetChanged()
    }

    companion object {

        const val NUMBER_OF_COLUMNS = 2

        fun newInstance(): SpecialBlendFragment {
            val fragment = SpecialBlendFragment()
            return fragment
        }
    }

    interface OnCardClickedListener {

        fun onFromSpecialBlendFragmentToggleLike(match: Match, isLiked: Boolean)
    }
}