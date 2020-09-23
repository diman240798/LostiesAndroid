package nanicky.losties.android.core.ui

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern


open class DecimalDigitsInputFilter : InputFilter {
    var mPattern: Pattern = Pattern.compile("[0-9]*+((\\.[0-9]?)?)||(\\.)?")

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): String? {
        val matcher: Matcher = mPattern.matcher(dest)
        return if (!matcher.matches()) "" else null
    }

}