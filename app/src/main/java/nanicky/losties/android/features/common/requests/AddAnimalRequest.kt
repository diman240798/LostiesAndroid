package nanicky.losties.android.features.common.requests

import nanicky.losties.android.features.model.Animal
import nanicky.losties.android.features.model.UserData
import nanicky.losties.android.features.entity.GeoAddress
import java.time.LocalDate

class AddAnimalRequest(
    var animal: Animal?= null,
    val user: UserData?= null,
    val geoAddress: GeoAddress?= null,
    val photos: List<ByteArray>
)
