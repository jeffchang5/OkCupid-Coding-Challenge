package io.jeffchang.okcupidcodingchallenge.ui.match.presenter

import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchView
import javax.inject.Inject

/**
 * Implementation of match presenter.
 */
class MatchPresenterImpl @Inject constructor(private val matchView: MatchView)
    : MatchPresenter {

    override fun onViewCreated() {
        matchView.showMatchList()
    }
}