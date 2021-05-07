package nanicky.losties.android.core.data.remote

import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.android.features.common.requests.AddAnimalRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("/lost/add")
    suspend fun addLostAnimal(@Body addAnimalRequest: AddAnimalRequest): Response<AddAnimalRequest>
    @POST("/seen/add")
    suspend fun addSeenAnimal(@Body addAnimalRequest: AddAnimalRequest): Response<AddAnimalRequest>
    @POST("/taken/add")
    suspend fun addFoundAnimal(@Body addAnimalRequest: AddAnimalRequest): Response<AddAnimalRequest>

    @GET("/lost/getAll")
    suspend fun getLostAnimals(): Response<List<AnimalPublication>>
    @GET("/seen/getAll")
    suspend fun getSeenAnimals(): Response<List<AnimalPublication>>
    @GET("/taken/getAll")
    suspend fun getTakenAnimals(): Response<List<AnimalPublication>>

    @POST("/lost/update")
    suspend fun updateLostAnimal(animal: AnimalPublication): Response<Unit>
    @POST("/seen/update")
    suspend fun updateSeenAnimal(animal: AnimalPublication): Response<Unit>
    @POST("/taken/update")
    suspend fun updateTakenAnimal(animal: AnimalPublication): Response<Unit>

//    @GET("/news/get")
//    suspend fun getNewPublications(): Response<List<Publication>>

}