package nanicky.losties.android.core.data.remote

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiAnimalRecognizer {
    @POST("/get_type")
    suspend fun requestAnimalTypeByPhoto(@Body image: RequestBody) : String
}