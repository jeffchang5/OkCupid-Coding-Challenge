package io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter

import io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor.SpecialBlendInteractor
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.MatchView

/**
 * Created by jeffreychang on 2/8/18.
 */

class MatchPresenterImpl internal constructor(val matchView: MatchView, specialBlendInteractor: SpecialBlendInteractor)
    : MatchPresenter {

    override fun onViewCreated() {
//        matchView.
    }

    override fun onCardClicked() {
    }

}