package io.jeffchang.okcupidcodingchallenge.ui.specialblend.view

import io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor.SpecialBlendInteractorImpl

/**
 * View contract for special blend.
 */

interface SpecialBlendView {

    fun onGetMatchesSuccess(matches: SpecialBlendInteractorImpl.CachedMatch)

    fun onGetMatchesFailure(throwable: Throwable)
}