package nanicky.losties.android.features.entity

import java.util.*

data class GeoAddress(
    val id: UUID,
    var longtitude: Double,
    var latitude: Double,
    val address: String
)