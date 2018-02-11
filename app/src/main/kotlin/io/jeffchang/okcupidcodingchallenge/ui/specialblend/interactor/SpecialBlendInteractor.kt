package io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor

import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Created by jeffreychang on 2/8/18.
 */

interface SpecialBlendInteractor {

    fun getMatches(): Flowable<ArrayList<Match>>

    fun getMatchesFromDb(): Maybe<List<Match>>

    fun getMatchesFromAPI(): Maybe<List<Match>>
}