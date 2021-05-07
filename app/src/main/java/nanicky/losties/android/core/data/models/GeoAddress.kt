package nanicky.losties.android.core.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class GeoAddress(
    val id: UUID,
    var longtitude: Double,
    var latitude: Double,
    val address: String
) : Parcelable