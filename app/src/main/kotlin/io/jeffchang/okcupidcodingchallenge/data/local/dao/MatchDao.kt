package io.jeffchang.okcupidcodingchallenge.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.reactivex.Maybe

/**
 * Database actions for Match.
 */
@Dao
abstract class MatchDao {

    @Transaction
    @Query("SELECT * FROM Match")
    abstract fun getMatches(): Maybe<List<Match>>
}