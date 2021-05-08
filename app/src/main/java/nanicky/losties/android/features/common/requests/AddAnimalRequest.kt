package nanicky.losties.android.features.common.requests

import nanicky.losties.android.core.data.models.Animal
import nanicky.losties.android.core.data.models.UserData
import nanicky.losties.android.core.data.models.GeoAddress

class AddAnimalRequest(
    var animal: Animal?= null,
    val userData: UserData?= null,
    val geoAddress: GeoAddress?= null,
    val photos: List<ByteArray>,
    val userId: String? = null
)
