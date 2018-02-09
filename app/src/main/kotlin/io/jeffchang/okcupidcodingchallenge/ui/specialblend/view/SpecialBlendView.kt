package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import io.jeffchang.okcupidcodingchallenge.data.model.Match

/**
 * Created by jeffreychang on 2/8/18.
 */

interface SpecialBlendView {

    fun onGetMatchesSuccess(matchResponse: List<Match?>?)

    fun onGetMatchesFailure(throwable: Throwable)

}