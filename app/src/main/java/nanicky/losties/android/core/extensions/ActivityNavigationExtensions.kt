package nanicky.losties.android.core.extensions

import android.content.Context
import android.content.Intent
import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.map.MapActivity
import nanicky.losties.android.features.map.MapActivityObject
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

fun Context.goToMapActivity(items: List<AnimalPublication>, dataObject: MapActivityObject) {
    dataObject.items = items
    val intent = Intent(this, MapActivity::class.java)
    startActivity(intent)
}
