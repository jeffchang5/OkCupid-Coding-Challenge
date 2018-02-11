package io.jeffchang.okcupidcodingchallenge.ui.common.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import java.util.TreeSet

/**
 * Created by jeffreychang on 2/9/18.
 */

class MatchRecyclerViewAdapter (private val context: Context,
                                private val matches: ArrayList<Match>,
                                private val keepLikeState: Boolean,
                                private val onCardClickedListener: MatchCardView.OnCardClickedListener?)
    : RecyclerView.Adapter<MatchRecyclerViewAdapter.MatchViewHolder>() {

    private var isMatchLiked: TreeSet<Int>? = null

    init {
        if (keepLikeState) isMatchLiked = TreeSet()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MatchViewHolder =
        MatchViewHolder(MatchCardView(context))

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder?, position: Int) {
        val matchViewHolder = (holder?.itemView as MatchCardView)
        val match = matches[position]
        matchViewHolder.onCardClickedListener = onCardClickedListener
        matchViewHolder.keepLikeState = keepLikeState
        matchViewHolder.updateUI(match)
    }

    class MatchViewHolder(view: View): RecyclerView.ViewHolder(view)
}
