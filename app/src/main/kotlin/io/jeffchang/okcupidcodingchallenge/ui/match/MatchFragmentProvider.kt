package io.jeffchang.okcupidcodingchallenge.ui.match

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchFragment

/**
 * Provides Match Fragment with its dependencies.
 */
@Module
abstract class MatchFragmentProvider {

    @ContributesAndroidInjector(modules = [MatchFragmentModule::class])
    abstract fun provideMatchFragment(): MatchFragment
}