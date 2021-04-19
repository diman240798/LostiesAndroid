package nanicky.losties.android.core.data.remote

import nanicky.losties.android.features.rateanimals.AnimalRandomRequest
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiAnimalRecognizer {
    @POST("/get_type")
    suspend fun requestAnimalTypeByPhoto(@Body image: RequestBody) : String

    @GET("/get_rand_animal")
    suspend fun getRandomAnimal(): Response<AnimalRandomRequest?>

    @GET("/get_rand_cat")
    suspend fun getRandomCat(): Response<AnimalRandomRequest?>

    @GET("/get_rand_dog")
    suspend fun getRandomDog(): Response<AnimalRandomRequest?>

    @POST("/rate_animal")
    suspend fun rateAnimal(@Query("name_photo") namePhoto: String, @Query("liked") liked: Boolean): Response<Unit>

}