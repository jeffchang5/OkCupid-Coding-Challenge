package io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor

import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.reactivex.Maybe

/**
 * Created by jeffreychang on 2/8/18.
 */

interface SpecialBlendInteractor {

    fun getMatchesFromAPI(): Maybe<ArrayList<Match>>

}