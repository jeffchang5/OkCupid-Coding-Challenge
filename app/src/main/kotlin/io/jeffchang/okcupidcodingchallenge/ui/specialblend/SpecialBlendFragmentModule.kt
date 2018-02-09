package io.jeffchang.okcupidcodingchallenge.ui.specialblend

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor.SpecialBlendInteractor
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor.SpecialBlendInteractorImpl
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.MatchPresenter
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.MatchPresenterImpl
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.MatchFragment
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.MatchView

/**
 * Created by jeffreychang on 2/8/18.
 */

@Module
abstract class SpecialBlendFragmentModule {

    @Binds
    abstract fun provideSpecialBlendView(matchFragment: MatchFragment): MatchView

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSpecialBlendPresenter(matchView: MatchView,
                                         specialBlendInteractor: SpecialBlendInteractor)
                : MatchPresenter =
                MatchPresenterImpl(matchView, specialBlendInteractor)

        @Provides
        @JvmStatic
        fun provideSpecialBlendInteractor(): SpecialBlendInteractor = SpecialBlendInteractorImpl()
    }
}