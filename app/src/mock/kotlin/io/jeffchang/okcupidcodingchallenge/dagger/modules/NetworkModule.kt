package io.jeffchang.okcupidcodingchallenge.dagger.modules

import dagger.Module
import dagger.Provides
import io.jeffchang.okcupidcodingchallenge.data.remote.MatchService
import io.jeffchang.okcupiddemo.dagger.modules.data.MockMatchService
import javax.inject.Singleton

/**
 * Module that exposes various objects needed to create our networking service.
 */

@Module
class NetworkModule constructor(url: String) {

    @Provides
    @Singleton
    fun provideMatchService(): MatchService {
        return MockMatchService()
    }
}