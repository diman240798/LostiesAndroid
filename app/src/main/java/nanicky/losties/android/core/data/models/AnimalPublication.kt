package nanicky.losties.android.core.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import nanicky.losties.android.features.entity.GeoAddress
import java.time.LocalDate
import java.util.*

@Parcelize
data class AnimalPublication(
    val id: UUID? = null,
    var animal: Animal? = null,
    val user: UserData? = null,
    val geoAddress: GeoAddress? = null,
    val date: LocalDate? = null
) : Parcelable