package io.jeffchang.okcupidcodingchallenge.data.local.dao

import android.arch.persistence.room.*
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.reactivex.Maybe

/**
 * Database actions for Match.
 */
@Dao
interface MatchDao {

    @Query("SELECT * FROM match ORDER BY liked DESC")
    fun getMatches(): Maybe<List<Match>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(match: Match)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListOfMatches(matches: List<Match>)
}