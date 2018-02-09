package io.jeffchang.okcupidcodingchallenge.ui.specialblend

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.SpecialBlendFragment

/**
 * Created by jeffreychang on 2/8/18.
 */

@Module
abstract class SpecialBlendFragmentProvider {

    @ContributesAndroidInjector(modules = [SpecialBlendFragmentModule::class])
    abstract fun provideSpecialBlendFragment(): SpecialBlendFragment
}