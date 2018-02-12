package io.jeffchang.okcupidcodingchallenge.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.jeffchang.okcupidcodingchallenge.di.components.MainActivityComponent
import javax.inject.Singleton

/**
 * Module that exposes the application context.
 */
@Module(subcomponents = [MainActivityComponent::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}