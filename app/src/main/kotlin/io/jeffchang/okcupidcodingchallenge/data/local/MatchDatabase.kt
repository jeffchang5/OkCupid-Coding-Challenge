package io.jeffchang.okcupidcodingchallenge.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.jeffchang.okcupidcodingchallenge.data.local.dao.MatchDao
import io.jeffchang.okcupidcodingchallenge.data.model.Match

/**
 * Declares daos for the entities represented within the SQLite database.
 */
@Database(entities = [
    Match::class
], version = 1, exportSchema = false)
abstract class MatchDatabase: RoomDatabase() {

    abstract fun matchDao(): MatchDao
}