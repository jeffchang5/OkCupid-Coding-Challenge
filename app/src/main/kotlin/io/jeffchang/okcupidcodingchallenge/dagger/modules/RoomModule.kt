package io.jeffchang.okcupidcodingchallenge.dagger.modules

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import io.jeffchang.okcupidcodingchallenge.data.local.MatchDatabase
import io.jeffchang.okcupidcodingchallenge.data.local.dao.MatchDao
import javax.inject.Singleton

/**
 * This module exposes a Room singleton.
 */
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): MatchDatabase =
            Room.databaseBuilder(application, MatchDatabase::class.java, "match-db")
                    .build()

    @Provides
    @Singleton
    fun provideMatchDao(matchDatabase: MatchDatabase): MatchDao =
            matchDatabase.matchDao()
}