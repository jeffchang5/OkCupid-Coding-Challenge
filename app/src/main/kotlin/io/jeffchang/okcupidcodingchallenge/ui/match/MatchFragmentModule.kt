package io.jeffchang.okcupidcodingchallenge.ui.match

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.jeffchang.okcupidcodingchallenge.ui.match.interactor.MatchInteractor
import io.jeffchang.okcupidcodingchallenge.ui.match.interactor.MatchInteractorImpl
import io.jeffchang.okcupidcodingchallenge.ui.match.presenter.MatchPresenterImpl
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchFragment
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchView
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.MatchPresenter

/**
 * Created by jeffreychang on 2/8/18.
 */

@Module
abstract class MatchFragmentModule {

    @Binds
    abstract fun provideMatchView(matchFragment: MatchFragment): MatchView

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSpecialBlendPresenter(matchView: MatchView,
                                         matchInteractor: MatchInteractor)
                : MatchPresenter =
                MatchPresenterImpl(matchView, matchInteractor)

        @Provides
        @JvmStatic
        fun provideSpecialBlendInteractor(): MatchInteractor =
                MatchInteractorImpl()
    }
}