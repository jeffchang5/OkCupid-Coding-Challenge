package io.jeffchang.okcupidcodingchallenge.ui.common.match

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.view.View
import com.squareup.picasso.Picasso
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.R.id.match_card_text_portion_layout
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import kotlinx.android.synthetic.main.view_match_card.view.*
import timber.log.Timber

/**
 * Created by jeffreychang on 2/9/18.
 */

class MatchCardView(context: Context): CardView(context) {

    var onCardClickedListener: OnCardClickedListener? = null

    var keepLikeState: Boolean? = null

    init {
        View.inflate(context, R.layout.view_match_card, this)
    }

    fun updateUI(match: Match) {
        Picasso.with(context)
                .load(match.photo?.fullPaths?.large)
                .into(match_card_profile_image_view)
        match_card_username_textview.text = match.username
        match_card_location_textview.text = String.format(
                resources.getString(R.string.match_card_profile_age_location),
                match.age,
                match.location?.cityName,
                match.location?.stateCode!!)
        match_card_match_percentage_textview.text = formatServerPercent(match.match)
        keepLikeState?.let { bool ->
            if (bool) addLikeBackground(match.liked)
            rootView.setOnClickListener({
                match.liked = !match.liked
                if (bool) addLikeBackground(match.liked)
                onCardClickedListener?.onCardClicked(match, match.liked) })
            }
    }

    private fun formatServerPercent(percent: Int): String {
        return String.format(resources.getString(R.string.match_percent),
                Math.round(percent * .01))
    }

    private fun addLikeBackground(isLiked: Boolean) {
        if (isLiked) {
            match_card_text_portion_layout.background =
                    ContextCompat.getDrawable(context, R.color.highlightYellow)
        } else {
            match_card_text_portion_layout.background =
                    ContextCompat.getDrawable(context, R.color.white)
        }
    }
    interface OnCardClickedListener {

        fun onCardClicked(match: Match, isLiked: Boolean)
    }
}