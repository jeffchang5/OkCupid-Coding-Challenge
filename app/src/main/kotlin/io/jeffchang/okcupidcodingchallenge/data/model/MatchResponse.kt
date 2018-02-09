package io.jeffchang.okcupidcodingchallenge.data.model

import com.squareup.moshi.Json

/**
 * Model that represents a sample response from the match endpoint
 */

data class MatchResponse(
        @Json(name = "total_matches") var totalMatches: Int?,
        @Json(name = "data") var data: List<Match?>?
)