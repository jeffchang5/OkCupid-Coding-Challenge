package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.ui.common.internet.MatchListFragment
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchCardView
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchRecyclerViewAdapter
import io.jeffchang.okcupidcodingchallenge.ui.main.MainActivity
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor.SpecialBlendInteractorImpl
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.SpecialBlendPresenter
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Fragment that manages the special blend model - a list of percentages for likely matches.
 */

class SpecialBlendFragment: MatchListFragment(),
        SpecialBlendView,
        MatchCardView.OnCardClickedListener {

    @Inject lateinit var specialBlendPresenter: SpecialBlendPresenter

    private var onCardClickedListener: OnCardClickedListener? = null

    private val matchList: ArrayList<Match> = ArrayList()

    private var matchRecyclerViewAdapter: MatchRecyclerViewAdapter? = null

    private lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCircularProgressBar("Loading Your Matches")
        specialBlendPresenter.onViewCreated()
        mainActivity = (activity as MainActivity)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnCardClickedListener)
            onCardClickedListener = context
    }

    override fun onCardClicked(match: Match, isLiked: Boolean) {
        specialBlendPresenter.onCardClicked(isLiked, match)
        onCardClickedListener?.onFromSpecialBlendFragmentToggleLike(match, isLiked)
    }

    override fun onGetMatchesSuccess(matches: SpecialBlendInteractorImpl.CachedMatch) {
        mainActivity.disableViewPager(false)
        matchList.clear()
        matchList.addAll(matches.matches)
        matches.matches
                .filter { it.liked }
                .forEach {
                    onCardClickedListener?.onFromSpecialBlendFragmentToggleLike(it, true) }
        if (!matches.isCached) specialBlendPresenter.onGetMatchesFromAPI(matches.matches)
        matchRecyclerViewAdapter =
                MatchRecyclerViewAdapter(context!!, matchList, true, this)
        recyclerView.adapter = matchRecyclerViewAdapter
        if (matchList.size > 0) {
            loadMainContent()
        }
    }

    override fun onGetMatchesFailure(throwable: Throwable) {
        mainActivity.disableViewPager(true)
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

    fun clearAllMatches() {
        matchList.forEach( {
            it.liked = false
        })
        matchRecyclerViewAdapter?.notifyDataSetChanged()
    }

    companion object {

        fun newInstance() = SpecialBlendFragment()
    }

    interface OnCardClickedListener {

        fun onFromSpecialBlendFragmentToggleLike(match: Match, isLiked: Boolean)
    }
}