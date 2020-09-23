package nanicky.losties.android.core.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File

fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide
            .with(this)
            .load(url)
            .into(this)
    }
}

fun ImageView.loadAvatarByFile(file: File) {

    val drawable = Drawable.createFromPath(file.absolutePath)

    if (file.exists()) {
        Glide
            .with(this)
            .load(drawable)
            .circleCrop()
            .into(this)
    }
}


