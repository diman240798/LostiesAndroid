package nanicky.losties.android.features.publishad

import nanicky.losties.android.core.data.remote.Api
import nanicky.losties.android.core.data.remote.ApiAnimalRecognizer
import nanicky.losties.android.features.common.requests.AddAnimalRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class PublishAdAnimalRepository(
    private val api: Api,
    private val apiAnimalRecognizer: ApiAnimalRecognizer
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

    suspend fun requestAnimalTypeByPhoto(imageFile: File): String {
        val fBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", imageFile.name, imageFile.asRequestBody("image/*".toMediaTypeOrNull()))
            .build()

        return apiAnimalRecognizer.requestAnimalTypeByPhoto(fBody)
    }
}