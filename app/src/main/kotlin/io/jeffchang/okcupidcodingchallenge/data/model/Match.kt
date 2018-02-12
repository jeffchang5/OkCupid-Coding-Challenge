package io.jeffchang.okcupidcodingchallenge.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Model that represents a match for an user.
 */

@Entity(tableName = "match")
data class Match(
        @PrimaryKey
        @Json(name = "userid") var userid: String,
        @Json(name = "enemy") var enemy: Int?,
        @Json(name = "relative") var relative: Long?,
        @Json(name = "last_login") var lastLogin: Int?,
        @Json(name = "gender") var gender: Int?,
        @Embedded
        @Json(name = "location") var location: Location?,
        @Json(name = "match") var match: Int,
        @Json(name = "liked") var liked: Boolean,
        @Json(name = "orientation") var orientation: Int?,
        @Embedded
        @Json(name = "photo") var photo: Photo?,
        @Json(name = "age") var age: Int?,
        @Json(name = "friend") var friend: Int?,
        @Json(name = "is_online") var isOnline: Int?,
        @Json(name = "username") var username: String?,
        @Json(name = "stoplight_color") var stoplightColor: String?
): Comparable<Match> {

    override fun compareTo(other: Match): Int = when {
        this.match > other.match -> -1
        this.match < other.match -> 1
        else -> 0
    }
}