package nanicky.losties.android.core.data.remote

import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.android.core.data.models.AnimalPublicationTyped
import nanicky.losties.android.features.common.requests.AddAnimalRequest
import nanicky.losties.android.features.enums.PublicationTypes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface Api {
    @POST("/lost/add")
    suspend fun addLostAnimal(@Body addAnimalRequest: AddAnimalRequest): Response<Unit>
    @POST("/seen/add")
    suspend fun addSeenAnimal(@Body addAnimalRequest: AddAnimalRequest): Response<Unit>
    @POST("/taken/add")
    suspend fun addTakenAnimal(@Body addAnimalRequest: AddAnimalRequest): Response<Unit>

    @GET("/lost/getAll")
    suspend fun getLostAnimals(): Response<List<AnimalPublication>>
    @GET("/seen/getAll")
    suspend fun getSeenAnimals(): Response<List<AnimalPublication>>
    @GET("/taken/getAll")
    suspend fun getTakenAnimals(): Response<List<AnimalPublication>>
    @GET("/lost/getAllUser")
    suspend fun getLostAnimalsForUser(@Query("userId") userId: String): Response<List<AnimalPublication>>
    @GET("/seen/getAllUser")
    suspend fun getSeenAnimalsForUser(@Query("userId") userId: String): Response<List<AnimalPublication>>
    @GET("/taken/getAllUser")
    suspend fun getTakenAnimalsForUser(@Query("userId") userId: String): Response<List<AnimalPublication>>

    @POST("/lost/update")
    suspend fun updateLostAnimal(animal: AnimalPublication): Response<Unit>
    @POST("/seen/update")
    suspend fun updateSeenAnimal(animal: AnimalPublication): Response<Unit>
    @POST("/taken/update")
    suspend fun updateTakenAnimal(animal: AnimalPublication): Response<Unit>

    @GET("/feed/get")
    suspend fun getNewPublications(): Response<List<AnimalPublicationTyped>>

    @POST("/bot/send-all")
    suspend fun publishOnSocialNetworks(@Query("publicationId") publicationId: UUID, @Query("publicationTypes") publicationTypes: PublicationTypes)
}