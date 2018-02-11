package io.jeffchang.okcupidcodingchallenge.ui.match.presenter

import io.jeffchang.okcupidcodingchallenge.ui.match.interactor.MatchInteractor
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchView
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.MatchPresenter
import javax.inject.Inject

/**
 * Created by jeffreychang on 2/8/18.
 */

class MatchPresenterImpl @Inject constructor(val matchView: MatchView,
                                             matchInteractor: MatchInteractor)
    : MatchPresenter {

    override fun onViewCreated() {
        matchView.showMatchList()
    }

    override fun onCardClicked() {
    }

}