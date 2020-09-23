package nanicky.losties.android.core.extensions

import kotlin.math.roundToInt

fun Double.roundTwoDigitsAfterDot(): Double {
    return (this * 100).roundToInt().toDouble() / 100
}