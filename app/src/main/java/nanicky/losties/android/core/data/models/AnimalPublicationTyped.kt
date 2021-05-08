package nanicky.losties.android.core.data.models

import java.util.*

class AnimalPublicationTyped(
    id: UUID? = null,
    animal: Animal? = null,
    userData: UserData? = null,
    geoAddress: GeoAddress? = null,
    date: String? = null,
    userId: String? = null,
    val publicationTypes: String
) : AnimalPublication(id, animal, userData, geoAddress, date, userId)