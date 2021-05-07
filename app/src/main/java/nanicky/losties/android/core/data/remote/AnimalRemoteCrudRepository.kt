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

    suspend fun addFoundAnimal(addAnimalRequest: AddAnimalRequest)
            = api.addFoundAnimal(addAnimalRequest)

    suspend fun requestAnimalTypeByPhoto(imageFile: File): String {
        val fBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", imageFile.name, imageFile.asRequestBody("image/*".toMediaTypeOrNull()))
            .build()

        return apiAnimalRecognizer.requestAnimalTypeByPhoto(fBody)
    }

    suspend fun getLostAnimals() = api.getLostAnimals()
    suspend fun getSeenAnimals() = api.getSeenAnimals()
    suspend fun getFoundAnimals() = api.getTakenAnimals()

    suspend fun updateLostAnimal(animal: AnimalPublication) = api.updateLostAnimal(animal)
    suspend fun updateSeenAnimal(animal: AnimalPublication) = api.updateSeenAnimal(animal)
    suspend fun updateTakenAnimal(animal: AnimalPublication) = api.updateTakenAnimal(animal)

//    suspend fun getNewPublications() = api.getNewPublications()
}