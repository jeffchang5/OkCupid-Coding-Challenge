package io.jeffchang.okcupidcodingchallenge.ui.match

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.jeffchang.okcupidcodingchallenge.ui.match.presenter.MatchPresenter
import io.jeffchang.okcupidcodingchallenge.ui.match.presenter.MatchPresenterImpl
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchFragment
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchView

/**
 * Provides dependency injection for Match Fragment.
 */
@Module
abstract class MatchFragmentModule {

    @Binds
    abstract fun provideMatchView(matchFragment: MatchFragment): MatchView

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideMatchPresenter(matchView: MatchView): MatchPresenter =
                MatchPresenterImpl(matchView)

    }
}