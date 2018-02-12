package io.jeffchang.okcupidcodingchallenge.util

import android.content.Context
import android.util.TypedValue

/**
 * A collection of methods that are useful in providing resources.
 */
object ResourceUtil {

    fun convertDpToPixel(context: Context, dim: Int): Int =
        TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                (dim.toFloat()),
                context.resources.displayMetrics).toInt()
}