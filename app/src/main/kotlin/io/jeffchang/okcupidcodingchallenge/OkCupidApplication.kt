package io.jeffchang.okcupidcodingchallenge

import com.crashlytics.android.Crashlytics
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.fabric.sdk.android.Fabric
import io.jeffchang.okcupidcodingchallenge.dagger.components.DaggerAppComponent
import timber.log.Timber

/**
 * Created by jeffreychang on 2/8/18.
 */

class OkCupidDemoApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {

            // Crash handling within the production application.
            Fabric.with(this, Crashlytics())
        }
    }
}