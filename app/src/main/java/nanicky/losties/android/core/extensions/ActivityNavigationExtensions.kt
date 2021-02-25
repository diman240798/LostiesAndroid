package nanicky.losties.android.core.extensions

import android.content.Context
import android.content.Intent
import nanicky.losties.android.features.showpublication.ShowPublicationActivity

fun Context.goToShowPublicationActivity() {
    startActivity(Intent(this, ShowPublicationActivity::class.java))
}