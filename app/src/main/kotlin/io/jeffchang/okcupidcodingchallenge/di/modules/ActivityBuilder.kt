package io.jeffchang.okcupidcodingchallenge.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jeffchang.okcupidcodingchallenge.ui.main.MainActivity
import io.jeffchang.okcupidcodingchallenge.ui.match.MatchFragmentProvider
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.SpecialBlendFragmentProvider

/**
 * Module that exposes the main view components.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
    SpecialBlendFragmentProvider::class,
    MatchFragmentProvider::class
    ])
    abstract fun bindMainActivity(): MainActivity
}