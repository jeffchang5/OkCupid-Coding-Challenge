package io.jeffchang.okcupidcodingchallenge.ui.specialblend

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.MatchFragment

/**
 * Created by jeffreychang on 2/8/18.
 */

@Module
abstract class MatchFragmentProvider {

    @ContributesAndroidInjector(modules = [MatchFragmentModule::class])
    abstract fun provideMatchFragment(): MatchFragment
}