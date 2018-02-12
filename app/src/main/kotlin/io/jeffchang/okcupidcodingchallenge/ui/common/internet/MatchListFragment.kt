package io.jeffchang.okcupidcodingchallenge.ui.common.internet

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout
import dagger.android.support.DaggerFragment
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.ui.common.match.MatchSpaceDecoration
import io.jeffchang.okcupidcodingchallenge.util.ResourceUtil

/**
 * This class is meant to be extended in providing logic for common
 * actions when retrieving data from the network.
 */
abstract class MatchListFragment : DaggerFragment() {

    private var layoutManagerState: Parcelable? = null

    private val parent by lazy {
        FrameLayout(context)
    }

    val recyclerView by lazy {
        RecyclerView(context)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        parent.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        recyclerView.layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        // If the device is a tablet, use 3 columns
        val numberOfColumns = if (resources.getBoolean(R.bool.isTablet)) 3 else 2
        recyclerView.layoutManager = GridLayoutManager(context, numberOfColumns)
        recyclerView.addItemDecoration(MatchSpaceDecoration(context!!,8))
        parent.addView(recyclerView)
        return parent
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerView.layoutManager?.let {
            outState.putParcelable(ARG_RECYCLER_VIEW_STATE, it.onSaveInstanceState())
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            layoutManagerState = it.getParcelable(ARG_RECYCLER_VIEW_STATE)
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.layoutManager?.onRestoreInstanceState(layoutManagerState)
    }
    
    /**
     * Loads a screen when there is no internet.
     *
     * @param[callback] When the try again text is pressed, it invokes a callback to handle
     * that event. Used to retry a network call.
     *
     * @param[height] When the main content view has a height of wrap content, the other
     * views will fit that respective height. This can cause the view to appear as if were
     * shrinking. This parameter can be either null to take on that property or to have a
     * set height to fall back on.
     */
    fun loadNoInternet(callback: (() -> Unit)?, height: Int?) {
        parent.removeAllViews()
        val noInternetView = ErrorView(context!!)
        if (height != null)
            noInternetView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,
                    ResourceUtil.convertDpToPixel(context!!, height))
        noInternetView.iconDrawable = R.drawable.ic_cloud_off_black_24dp
        noInternetView.titleText = R.string.device_offline
        noInternetView.tryAgainCallback = {
            loadCircularProgressBar("Trying again...")
            callback?.invoke()
        }
        parent.addView(noInternetView)
    }

    /**
     * Loads a screen when there is an unknown error.
     *
     * @param[callback] When the try again text is pressed, it invokes a callback to handle
     * that event. Used to retry a network call.
     *
     * @param[height] When the main content view has a height of wrap content, the other
     * views will fit that respective height. This can cause the view to appear as if were
     * shrinking. This parameter can be either null to take on that property or to have a
     * set height to fall back on.
     */
    fun loadUnknownReason(callback: (() -> Unit)?, height: Int?) {
        parent.removeAllViews()
        val unknownReasonView = ErrorView(context!!)
        if (height != null)
            unknownReasonView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,
                    ResourceUtil.convertDpToPixel(context!!, height))
        unknownReasonView.iconDrawable = R.drawable.ic_error_outline_black_24dp
        unknownReasonView.titleText = R.string.unknown_error
        unknownReasonView.tryAgainCallback = {
            loadCircularProgressBar("Trying again...")
            callback?.invoke()
        }
        parent.addView(unknownReasonView)
    }

    /**
     * Loads a view with a progress bar
     *
     * @param[message] Sets the text within the loading message
     */
    fun loadCircularProgressBar(message: String) {
        parent.removeAllViews()
        val circularProgressView = CircularProgressView(context!!)
        circularProgressView.loadingText = message
        parent.addView(circularProgressView)
    }

    /**
     * Loads a view with the RecyclerView
     */
    fun loadMainContent() {
        parent.removeAllViews()
        parent.addView(recyclerView)
    }

    companion object {

        private const val ARG_RECYCLER_VIEW_STATE = "ARG_RECYCLER_VIEW_STATE"
    }
}