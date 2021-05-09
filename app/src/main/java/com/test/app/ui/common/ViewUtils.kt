package com.test.app.ui.common

import android.content.Context

fun pxFromDp(context: Context?, dp: Float): Float {
    return dp * context?.resources?.displayMetrics?.density!!
}

fun dpFromPx(context: Context?, px: Float): Float {
    return px / context?.resources?.displayMetrics?.density!!;
}