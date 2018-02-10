package io.jeffchang.okcupidcodingchallenge.data.remote

import io.jeffchang.okcupidcodingchallenge.data.model.MatchResponse
import io.reactivex.Maybe
import retrofit2.http.GET

/**
 * Service that represents the RESTful interactions with the match endpoint
 */

interface MatchService {
    @GET("matchSample.json")
    fun getMatch(): Maybe<MatchResponse>
}