package nanicky.losties.android.core.ui

import android.text.Spanned

class DecimalDigitsInputFilterLessThen(val maxValue: Int) : DecimalDigitsInputFilter() {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): String? {
        if (super.filter(source, start, end, dest, dstart, dend) == "") return ""
        try {
            val number = (dest.toString() + source).toDouble()
            if (number > maxValue) return ""
            return null
        } catch (ex: Throwable) {
            return ""
        }
    }

}