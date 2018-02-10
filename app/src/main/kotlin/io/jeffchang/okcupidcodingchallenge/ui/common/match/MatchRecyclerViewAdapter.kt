package io.jeffchang.okcupidcodingchallenge.ui.common.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.jeffchang.okcupidcodingchallenge.data.model.Match

/**
 * Created by jeffreychang on 2/9/18.
 */

class MatchRecyclerViewAdapter (private val context: Context,
                                private val matches: List<Match>,
                                private val onCardClickedListener: MatchCardView.OnCardClickedListener)
    : RecyclerView.Adapter<MatchRecyclerViewAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MatchViewHolder =
        MatchViewHolder(MatchCardView(context))

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder?, position: Int) {
        val matchViewHolder = (holder?.itemView as MatchCardView)
        matchViewHolder.onCardClickedListener = onCardClickedListener
        matchViewHolder.updateUI(matches[position])
    }

    class MatchViewHolder(view: View): RecyclerView.ViewHolder(view)
}
