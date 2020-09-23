package nanicky.losties.android.core.extensions

import android.content.Context
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import nanicky.losties.android.R

fun AlertDialog.setTypefaceInDialog(context: Context) {

    val regularFont = ResourcesCompat.getFont(context, R.font.ubuntu_r)
    val boldFont = ResourcesCompat.getFont(context, R.font.ubuntu_r)

    findViewById<TextView>(androidx.appcompat.R.id.alertTitle)?.typeface = boldFont
    findViewById<TextView>(android.R.id.message)?.typeface = regularFont

    val positiveButton = getButton(AlertDialog.BUTTON_POSITIVE)
    positiveButton?.typeface = boldFont
    positiveButton?.setTextColor(ContextCompat.getColor(context, R.color.colorRed))

    getButton(AlertDialog.BUTTON_NEGATIVE)?.typeface = boldFont
}