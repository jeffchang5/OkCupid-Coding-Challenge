package io.jeffchang.okcupidcodingchallenge.ui.common.match

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import io.jeffchang.okcupidcodingchallenge.util.ResourceUtil

/**
 * Provides spacing for items on the GridLayoutManager
 *
 * [Gist for equal spacing on GridLayoutManager] (https://gist.github.com/yqritc/ccca77dc42f2364777e1)
 */
class MatchSpaceDecoration(val context: Context,
                           private val spaceInDp: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val dpInPixel = ResourceUtil.convertDpToPixel(context, spaceInDp)
        outRect.set(dpInPixel, dpInPixel, dpInPixel, dpInPixel)
    }
}