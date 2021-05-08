package nanicky.losties.android.core.utils

import nanicky.losties.android.core.di.PHOTO_URL_POINT
import nanicky.losties.android.features.enums.PublicationTypes
import java.util.*

fun getImageUrl(photos: List<UUID>, type: PublicationTypes): String? {
    if (photos.isEmpty()) return null
    val photoId = photos[0]
    return getImageUrl(photoId, type)
}

fun getImageUrl(photoId: UUID, type: PublicationTypes): String {
    return String.format(PHOTO_URL_POINT, photoId, type)
}