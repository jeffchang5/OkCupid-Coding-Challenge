package io.jeffchang.okcupidcodingchallenge.di.modules

import dagger.Module
import dagger.Provides
import io.jeffchang.okcupidcodingchallenge.data.remote.MatchService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Module that exposes various objects needed to create our networking service.
 */

@Module
class NetworkModule constructor(private val url: String) {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
    }

    @Provides
    @Singleton
    fun provideMatchService(retrofit: Retrofit): MatchService {
        return retrofit.create(MatchService::class.java)
    }
}