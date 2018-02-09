package io.jeffchang.okcupidcodingchallenge.ui.common.internet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout
import dagger.android.support.DaggerFragment
import io.jeffchang.okcupidcodingchallenge.util.ResourceUtil

/**
 * This class is meant to be extended in providing logic for common
 * actions when retrieving data from the network.
 */
abstract class InternetFragment : DaggerFragment() {

    private lateinit var parent: FrameLayout

    private var childView: View? = null

    abstract var layoutResourceID: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        parent = FrameLayout(context)
        parent.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        childView = inflater.inflate(layoutResourceID, container, false)
        parent.addView(childView)
        return parent
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
        val noInternetView = NoInternetView(context!!)
        if (height != null)
            noInternetView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,
                    ResourceUtil.convertDpToPixel(context!!, height))
        noInternetView.tryAgainCallback = {
            loadCircularProgressBar("Trying again...")
            callback?.invoke()
        }
        parent.addView(noInternetView)
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
     * Loads a view with the contents inflated by [layoutResourceID]
     */
    fun loadMainContent() {
        parent.removeAllViews()
        parent.addView(childView)
    }
}