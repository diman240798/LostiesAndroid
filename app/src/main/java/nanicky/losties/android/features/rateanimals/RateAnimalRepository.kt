package nanicky.losties.android.features.rateanimals

import nanicky.losties.android.core.data.remote.Api
import nanicky.losties.android.core.data.remote.ApiAnimalRecognizer
import retrofit2.Response

class RateAnimalRepository(
    private val api: Api,
    private val apiAnimalRecognizer: ApiAnimalRecognizer
) {

    suspend fun getRandomAnimal(): AnimalRandomRequest? = apiAnimalRecognizer.getRandomAnimal().body()

    suspend fun getRandomCat(): AnimalRandomRequest? = apiAnimalRecognizer.getRandomCat().body()

    suspend fun getRandomDog(): AnimalRandomRequest? = apiAnimalRecognizer.getRandomDog().body()

    suspend fun rateAnimal(namePhoto: String, liked: Boolean) = apiAnimalRecognizer.rateAnimal(namePhoto, liked)
}