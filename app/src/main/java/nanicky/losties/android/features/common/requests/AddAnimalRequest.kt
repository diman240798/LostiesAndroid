package nanicky.losties.android.features.common.requests

import nanicky.losties.android.core.data.models.Animal
import nanicky.losties.android.core.data.models.UserData
import nanicky.losties.android.features.entity.GeoAddress

class AddAnimalRequest(
    var animal: Animal?= null,
    val user: UserData?= null,
    val geoAddress: GeoAddress?= null,
    val photos: List<ByteArray>
)
