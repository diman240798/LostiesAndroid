package nanicky.losties.android.core.data.remote

import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.android.features.common.requests.AddAnimalRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class AnimalRemoteCrudRepository(
    private val api: Api,
    private val apiAnimalRecognizer: ApiAnimalRecognizer
) {

    suspend fun addLostAnimal(addAnimalRequest: AddAnimalRequest)
            = api.addLostAnimal(addAnimalRequest)

    suspend fun addSeenAnimal(addAnimalRequest: AddAnimalRequest)
            = api.addSeenAnimal(addAnimalRequest)

    suspend fun addTakenAnimal(addAnimalRequest: AddAnimalRequest)
            = api.addTakenAnimal(addAnimalRequest)

    suspend fun requestAnimalTypeByPhoto(imageFile: File): String {
        val fBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", imageFile.name, imageFile.asRequestBody("image/*".toMediaTypeOrNull()))
            .build()

        return apiAnimalRecognizer.requestAnimalTypeByPhoto(fBody)
    }

    suspend fun getLostAnimals(userId: String?) = if (userId != null) api.getLostAnimalsForUser(userId) else api.getLostAnimals()
    suspend fun getSeenAnimals(userId: String?) = if (userId != null) api.getSeenAnimalsForUser(userId) else api.getSeenAnimals()
    suspend fun getTakenAnimals(userId: String?) = if (userId != null) api.getTakenAnimalsForUser(userId) else api.getTakenAnimals()

    suspend fun updateLostAnimal(animal: AnimalPublication) = api.updateLostAnimal(animal)
    suspend fun updateSeenAnimal(animal: AnimalPublication) = api.updateSeenAnimal(animal)
    suspend fun updateTakenAnimal(animal: AnimalPublication) = api.updateTakenAnimal(animal)

    suspend fun getNewPublications() = api.getNewPublications()
}