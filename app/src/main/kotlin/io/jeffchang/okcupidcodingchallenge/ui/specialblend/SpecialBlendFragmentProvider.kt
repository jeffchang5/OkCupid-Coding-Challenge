package io.jeffchang.okcupidcodingchallenge.ui.specialblend

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.SpecialBlendFragment

/**
 * Injects dependencies into special blend fragment.
 */
@Module
abstract class SpecialBlendFragmentProvider {

    @ContributesAndroidInjector(modules = [SpecialBlendFragmentModule::class])
    abstract fun provideSpecialBlendFragment(): SpecialBlendFragment
}