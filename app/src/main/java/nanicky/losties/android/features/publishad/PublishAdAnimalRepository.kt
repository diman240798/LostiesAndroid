package nanicky.losties.android.features.publishad

import nanicky.losties.android.core.data.remote.Api
import nanicky.losties.android.features.common.requests.AddAnimalRequest

class PublishAdAnimalRepository(
    val api: Api
) {

    suspend fun addLostAnimal(addAnimalRequest: AddAnimalRequest) {
        api.addLostAnimal(addAnimalRequest)
    }

    suspend fun addSeenAnimal(addAnimalRequest: AddAnimalRequest) {
        api.addSeenAnimal(addAnimalRequest)
    }

    suspend fun addFoundAnimal(addAnimalRequest: AddAnimalRequest) {
        api.addFoundAnimal(addAnimalRequest)
    }
}