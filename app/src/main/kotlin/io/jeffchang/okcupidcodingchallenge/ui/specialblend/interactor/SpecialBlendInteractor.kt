package io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor

import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Interactor for special blend.
 */
interface SpecialBlendInteractor {

    fun getMatches(): Flowable<ArrayList<Match>>

    fun getMatchesFromDb(): Maybe<List<Match>>

    fun getMatchesFromAPI(): Maybe<List<Match>>
}