package io.jeffchang.okcupidcodingchallenge.dagger.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import io.jeffchang.okcupidcodingchallenge.OkCupidDemoApplication
import io.jeffchang.okcupidcodingchallenge.dagger.modules.ActivityBuilder
import io.jeffchang.okcupidcodingchallenge.dagger.modules.AppModule
import io.jeffchang.okcupidcodingchallenge.dagger.modules.NetworkModule
import io.jeffchang.okcupidcodingchallenge.dagger.modules.RoomModule
import javax.inject.Singleton

/**
 * Component that injects into Android members (e.g. Activities and Fragments) with various
 * modules that provide tasks such as networking and caching in a database.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    NetworkModule::class,
    RoomModule::class
])
interface AppComponent: AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    fun inject(application: OkCupidDemoApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun roomModule(roomModule: RoomModule): Builder

        fun build(): AppComponent
    }
}