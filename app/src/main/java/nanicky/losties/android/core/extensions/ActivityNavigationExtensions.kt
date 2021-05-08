package nanicky.losties.android.core.extensions

import android.content.Context
import android.content.Intent
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.watchpublication.WatchPublicationActivity
import nanicky.losties.android.features.watchpublicationlist.WatchPublicationListActivity

fun Context.goToShowPublicationActivity(publicationType: PublicationTypes, userId: String?= null) {
    val intent = Intent(this, WatchPublicationActivity::class.java)
    intent.putExtra(WatchPublicationActivity.ANIMAL_TYPE_EXTRA, publicationType.name)
    intent.putExtra(WatchPublicationActivity.USER_ID_EXTRA, userId)
    startActivity(intent)
}

fun Context.goToWatchPublicationsListActivity(publicationType: PublicationTypes, userId: String?= null) {
    val intent = Intent(this, WatchPublicationListActivity::class.java)
    intent.putExtra(WatchPublicationListActivity.ANIMAL_TYPE_EXTRA, publicationType.name)
    intent.putExtra(WatchPublicationListActivity.USER_ID_EXTRA, userId)
    startActivity(intent)
}