package io.jeffchang.okcupidcodingchallenge.ui.common.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import java.util.TreeSet

/**
 * Binds matches to create cards on a RecyclerView.
 *
 * @property[isMatchLiked] Set when matches are persisted.
 */
class MatchRecyclerViewAdapter (private val context: Context,
                                private val matches: ArrayList<Match>,
                                private val keepLikeState: Boolean,
                                private val onCardClickedListener: MatchCardView.OnCardClickedListener?)
    : RecyclerView.Adapter<MatchRecyclerViewAdapter.MatchViewHolder>() {

    private val isMatchLiked: TreeSet<Int> by lazy {
        TreeSet<Int>()
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
