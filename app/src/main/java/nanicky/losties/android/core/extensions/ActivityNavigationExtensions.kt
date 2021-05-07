package nanicky.losties.android.core.extensions

import android.content.Context
import android.content.Intent
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.publishad.PublishAdAnimalActivity
import nanicky.losties.android.features.showpublication.ShowPublicationActivity

fun Context.goToShowPublicationActivity(publicationType: PublicationTypes) {
    val intent = Intent(this, ShowPublicationActivity::class.java)
    intent.putExtra(ShowPublicationActivity.ANIMAL_TYPE_EXTRA, publicationType.name)
    startActivity(intent)
}