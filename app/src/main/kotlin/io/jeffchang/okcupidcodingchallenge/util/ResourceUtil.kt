package io.jeffchang.okcupidcodingchallenge.util

import android.content.Context
import android.support.annotation.DimenRes
import android.util.TypedValue

/**
 * A collection of methods that are useful in providing resources.
 */

object ResourceUtil {

    fun convertDpResToPixel(context: Context, @DimenRes res: Int): Int {
        return context.resources.getDimensionPixelSize(res)
    }

    fun convertDpToPixel(context: Context, dim: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (dim.toFloat()),
                context.resources.displayMetrics)
                .toInt()
    }
}