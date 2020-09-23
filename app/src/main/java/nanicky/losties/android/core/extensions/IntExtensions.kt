package nanicky.losties.android.core.extensions

import android.content.res.Resources

fun Int.withZero(): String = if (this < 10) "0$this" else this.toString()

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()