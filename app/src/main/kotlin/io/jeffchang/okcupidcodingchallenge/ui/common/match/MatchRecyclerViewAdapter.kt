package io.jeffchang.okcupidcodingchallenge.ui.common.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.jeffchang.okcupidcodingchallenge.data.model.Match

/**
 * Created by jeffreychang on 2/9/18.
 */

class MatchRecyclerViewAdapter constructor(private val context: Context,
                                           private val matches: List<Match>?)
    : RecyclerView.Adapter<MatchRecyclerViewAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MatchViewHolder =
        MatchViewHolder(MatchCardView(context))

    override fun getItemCount(): Int =  5 // matches!!.size

    override fun onBindViewHolder(holder: MatchViewHolder?, position: Int) {

    }

    class MatchViewHolder(view: View): RecyclerView.ViewHolder(view)
}
