package io.jeffchang.okcupidcodingchallenge.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.jeffchang.okcupidcodingchallenge.data.local.dao.MatchDao
import io.jeffchang.okcupidcodingchallenge.data.model.Match

/**
 * Created by jeffreychang on 2/11/18.
 */

@Database(entities = [
    Match::class
], version = 1, exportSchema = false)
abstract class MatchDatabase: RoomDatabase() {

    abstract fun matchDao(): MatchDao
}