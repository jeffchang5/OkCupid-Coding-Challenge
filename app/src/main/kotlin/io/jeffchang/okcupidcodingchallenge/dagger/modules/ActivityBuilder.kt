package io.jeffchang.okcupidcodingchallenge.dagger.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jeffchang.okcupidcodingchallenge.MainActivity

/**
 * Module that exposes the main view components.
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [

    ])
    abstract fun bindMainActivity(): MainActivity
}