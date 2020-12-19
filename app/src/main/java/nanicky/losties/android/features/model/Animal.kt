package nanicky.losties.android.features.model

import nanicky.losties.losties.util.AnimalType
import java.util.*

data class Animal(val id: UUID ?= null, var name: String?= null, var type: AnimalType ?= null, var breed: String?= null, var photoIds: List<UUID>?= null)