package nanicky.losties.android.core.extensions

import android.content.Intent
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.features.showpublication.ShowPublicationActivity


fun BaseActivity.goToShowPublicationActivity() {
    startActivity(Intent(this, ShowPublicationActivity.javaClass))
}