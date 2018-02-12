package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import android.content.Context
import android.os.Bundle
import android.view.View
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

    private val mainActivity by lazy {
        activity as MainActivity
    }

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
        specialBlendPresenter.onCardStatusChanged(isLiked, match)
        onCardClickedListener?.onFromSpecialBlendFragmentToggleLike(match, isLiked)
    }

    /*
    Checks Cache -> Internet -> View

    If data is retrieved from the server instead of local storage, insert the matches into the
     local storage. This way, the liked data isn't overwritten from the server data
     */
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

    /**
     * Handles the case if there is a network issue or otherwise.
     */
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
        match.liked = false
        specialBlendPresenter.onCardStatusChanged(false, match)
        matchRecyclerViewAdapter?.notifyDataSetChanged()
    }

    /**
     * Resets all matches to be "unliked"
     */
    fun clearAllMatchesLikes() {
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