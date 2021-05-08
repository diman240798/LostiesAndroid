package nanicky.losties.android.core.data.models

import java.util.*

open class AnimalPublication(
        val id: UUID? = null,
        var animal: Animal? = null,
        val userData: UserData? = null,
        val geoAddress: GeoAddress? = null,
        val date: String? = null,
        val userId: String? = null
)