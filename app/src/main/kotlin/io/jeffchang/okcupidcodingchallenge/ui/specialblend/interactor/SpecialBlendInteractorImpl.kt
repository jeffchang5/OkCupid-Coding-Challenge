package io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor

import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.data.remote.MatchService
import io.reactivex.Maybe

/**
 * Created by jeffreychang on 2/8/18.
 */

class SpecialBlendInteractorImpl constructor(private val matchService: MatchService)
    : SpecialBlendInteractor {

    override fun getMatchesFromAPI(): Maybe<List<Match?>?>
            = matchService.getMatch().map { it.data }
}