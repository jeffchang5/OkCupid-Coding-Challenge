package io.jeffchang.okcupidcodingchallenge.ui.specialblend

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.jeffchang.okcupidcodingchallenge.data.local.MatchDatabase
import io.jeffchang.okcupidcodingchallenge.data.local.dao.MatchDao
import io.jeffchang.okcupidcodingchallenge.data.remote.MatchService
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor.SpecialBlendInteractor
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor.SpecialBlendInteractorImpl
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.SpecialBlendPresenter
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.SpecialBlendPresenterImpl
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.SpecialBlendFragment
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.SpecialBlendView

/**
 * Created by jeffreychang on 2/8/18.
 */

@Module
abstract class SpecialBlendFragmentModule {

    @Binds
    abstract fun provideSpecialBlendView(specialBlendFragment: SpecialBlendFragment): SpecialBlendView

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSpecialBlendPresenter(specialBlendView: SpecialBlendView,
                                         specialBlendInteractor: SpecialBlendInteractor)
                : SpecialBlendPresenter =
                SpecialBlendPresenterImpl(specialBlendView, specialBlendInteractor)

        @Provides
        @JvmStatic
        fun provideSpecialBlendInteractor(matchService: MatchService,
                                          matchDao: MatchDao)
                : SpecialBlendInteractor = SpecialBlendInteractorImpl(matchService, matchDao)
    }
}