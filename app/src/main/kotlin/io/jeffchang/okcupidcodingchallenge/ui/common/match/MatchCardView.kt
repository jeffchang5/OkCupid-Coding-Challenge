package io.jeffchang.okcupidcodingchallenge.ui.common.match

import android.content.Context
import android.support.v7.widget.CardView
import android.view.View
import com.squareup.picasso.Picasso
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import kotlinx.android.synthetic.main.view_match_card.view.*

/**
 * Created by jeffreychang on 2/9/18.
 */

class MatchCardView(context: Context): CardView(context) {

    init {
        View.inflate(context, R.layout.view_match_card, this)
    }

    fun updateUI(match: Match) {
        Picasso.with(context)
                .load(match.photo?.fullPaths?.medium)
                .into(match_card_profile_image_view)
        match_card_username_textview.text = match.username
        match_card_location_textview.text = String.format(
                resources.getString(R.string.match_card_profile_age_location),
                match.age,
                match.location?.cityName,
                match.location?.stateCode!!)
        match_card_match_percentage_textview.text = formatServerPercent(match.match!!)
    }

    private fun formatServerPercent(percent: Int): String {
        return String.format(resources.getString(R.string.match_percent),
                Math.round(percent * .01))
    }
}