package io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter

import io.jeffchang.okcupidcodingchallenge.data.model.Match

/**
 * Presenter for special blend.
 */
interface SpecialBlendPresenter {

    fun onViewCreated()

    fun onCardClicked(isLiked: Boolean, match: Match)

    fun onGetMatchesFromAPI(matches: ArrayList<Match>)
}