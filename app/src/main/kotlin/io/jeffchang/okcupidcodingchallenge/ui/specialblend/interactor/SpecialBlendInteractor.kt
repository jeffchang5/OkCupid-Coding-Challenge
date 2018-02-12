package io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor

import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Interactor for special blend.
 */
interface SpecialBlendInteractor {

    fun getMatches(): Observable<SpecialBlendInteractorImpl.CachedMatch>

    fun getMatchesFromDb(): Observable<List<Match>>

    fun getMatchesFromAPI(): Observable<List<Match>>

    fun insertMatchToDb(isLiked: Boolean, match: Match): Single<Unit>?

    fun insertMatchesToDb(matches: ArrayList<Match>): Single<Unit>
}