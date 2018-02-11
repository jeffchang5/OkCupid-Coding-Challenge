package io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter

import io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor.SpecialBlendInteractor
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.SpecialBlendView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Implementation of special blend presenter.
 */
class SpecialBlendPresenterImpl @Inject constructor(
        private val specialBlendView: SpecialBlendView,
        private val specialBlendInteractor: SpecialBlendInteractor): SpecialBlendPresenter {

    override fun onViewCreated() {
        specialBlendInteractor
                .getMatches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    specialBlendView.onGetMatchesSuccess(it)
                }, {
                    specialBlendView.onGetMatchesFailure(it)
                })
    }

    override fun onCardClicked() {

    }
}