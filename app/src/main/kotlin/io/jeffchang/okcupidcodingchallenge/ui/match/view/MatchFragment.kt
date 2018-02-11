package io.jeffchang.okcupidcodingchallenge.ui.match.view

import android.content.Context
import android.os.Bundle
import android.view.View
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.ui.common.internet.MatchListFragment
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchCardView
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchRecyclerViewAdapter
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.MatchPresenter
import java.util.TreeSet
import javax.inject.Inject

/**
 * Created by jeffreychang on 2/8/18.
 */

class MatchFragment : MatchListFragment(), MatchView, MatchCardView.OnCardClickedListener {

    @Inject lateinit var matchPresenter: MatchPresenter

    private var onCardClickedListener: OnCardClickedListener? = null

    private var matchRecyclerViewAdapter: MatchRecyclerViewAdapter? = null

    private val matchSet by lazy {
        TreeSet<Match>()
    }

    private val matchList by lazy {
        ArrayList<Match>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchPresenter.onViewCreated()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnCardClickedListener) {
            onCardClickedListener = context
        }
    }

    fun addMatchToAdapter(match: Match) {
        matchSet.add(match)
        matchList.clear()
        matchList.addAll(matchSet)
        matchRecyclerViewAdapter?.notifyDataSetChanged()
    }

    fun removeMatchToAdapter(match: Match) {
        matchSet.remove(match)
        matchList.clear()
        matchList.addAll(matchSet)
        matchRecyclerViewAdapter?.notifyDataSetChanged()
    }

    override fun showMatchList() {
        matchRecyclerViewAdapter = MatchRecyclerViewAdapter(context!!, matchList,
                false, this)
        recyclerView.adapter = matchRecyclerViewAdapter
    }

    override fun onCardClicked(match: Match, isLiked: Boolean) {
        val matchIndex = matchList.indexOf(match)
        matchSet.remove(match)
        matchList.removeAt(matchIndex)
        matchRecyclerViewAdapter?.notifyItemRemoved(matchIndex)
        matchRecyclerViewAdapter?.notifyItemRangeChanged(matchIndex, matchList.size)
        onCardClickedListener?.onFromMatchFragmentRemoveLike(match)
    }

    companion object {

        fun newInstance() = MatchFragment()
    }

    interface OnCardClickedListener {

        fun onFromMatchFragmentRemoveLike(match: Match)
    }
}