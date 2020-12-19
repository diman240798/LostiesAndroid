package nanicky.losties.android.core.data.remote

import nanicky.losties.android.features.common.requests.AddAnimalRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("/lost/add")
    suspend fun addLostAnimal(@Body addAnimalRequest: AddAnimalRequest): Response<AddAnimalRequest>
    @POST("/seen/add")
    suspend fun addSeenAnimal(@Body addAnimalRequest: AddAnimalRequest): Response<AddAnimalRequest>
    @POST("/found/add")
    suspend fun addFoundAnimal(@Body addAnimalRequest: AddAnimalRequest): Response<AddAnimalRequest>
}