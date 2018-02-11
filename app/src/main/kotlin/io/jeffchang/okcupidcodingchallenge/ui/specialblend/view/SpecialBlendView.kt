package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import io.jeffchang.okcupidcodingchallenge.data.model.Match

/**
 * View contract for special blend.
 */

interface SpecialBlendView {

    fun onGetMatchesSuccess(matches: ArrayList<Match>)

    fun onGetMatchesFailure(throwable: Throwable)
}