package nanicky.losties.android.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}

inline fun <reified T : Activity> Context.open() =
    startActivity(Intent(this, T::class.java))


fun Context.getDrawableCompat(@DrawableRes drawable: Int)
        = ContextCompat.getDrawable(this, drawable)

fun Context.getColorCompat(@ColorRes color: Int)
        = ContextCompat.getColor(this, color)
