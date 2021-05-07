package nanicky.losties.android.core.data.models

import java.util.*

data class AnimalPublication(
        val id: UUID? = null,
        var animal: Animal? = null,
        val user: UserData? = null,
        val geoAddress: GeoAddress? = null,
        val date: String? = null
)