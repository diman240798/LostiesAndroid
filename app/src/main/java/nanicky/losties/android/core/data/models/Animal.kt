package nanicky.losties.android.core.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import nanicky.losties.losties.util.AnimalType
import java.util.*

@Parcelize
data class Animal(val id: UUID ?= null, var name: String?= null, var type: AnimalType ?= null, var breed: String?= null, var photoIds: List<UUID>?= null) :
    Parcelable