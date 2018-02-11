package io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor

import io.jeffchang.okcupidcodingchallenge.data.local.dao.MatchDao
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.data.remote.MatchService
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Implementation of special blend interactor.
 */
class SpecialBlendInteractorImpl constructor(private val matchService: MatchService,
                                             private val matchDao: MatchDao)
    : SpecialBlendInteractor {
    override fun getMatches(): Flowable<ArrayList<Match>> =
            Maybe.concatArray(getMatchesFromDb(), getMatchesFromAPI())
                    .map { ArrayList(it) }

    override fun getMatchesFromDb(): Maybe<List<Match>> = matchDao.getMatches()


    override fun getMatchesFromAPI(): Maybe<List<Match>>
            = matchService.getMatch().map { it.data }
}