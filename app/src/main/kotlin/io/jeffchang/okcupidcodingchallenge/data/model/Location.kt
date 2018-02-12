package io.jeffchang.okcupidcodingchallenge.data.model

import com.squareup.moshi.Json

/**
 * Model that represents a user's location
 */
data class Location(
        @Json(name = "country_code") var countryCode: String?,
        @Json(name = "city_name") var cityName: String?,
        @Json(name = "country_name") var countryName: String?,
        @Json(name = "state_name") var stateName: String?,
        @Json(name = "state_code") var stateCode: String?
)