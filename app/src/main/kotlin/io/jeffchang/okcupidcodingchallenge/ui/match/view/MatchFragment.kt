package io.jeffchang.okcupidcodingchallenge.ui.match

import android.content.Context
import android.os.Bundle
import android.view.View
import io.jeffchang.okcupidcodingchallenge.ui.common.internet.MatchListFragment
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchView
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.presenter.MatchPresenter
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by jeffreychang on 2/8/18.
 */

class MatchFragment : MatchListFragment(), MatchView {

    private var onCardClickedListener: OnCardClickedListener? = null

    @Inject
    lateinit var matchPresenter: MatchPresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnCardClickedListener)
            onCardClickedListener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchPresenter.onViewCreated()
    }

    companion object {
        fun newInstance(): MatchFragment {
            val fragment = MatchFragment()
            return fragment
        }
    }
    
    override fun showMatchList() {
        Timber.e("asdfs")
    }

    interface OnCardClickedListener {

        fun onFromMatchFragmentRemoveLike()
    }
}