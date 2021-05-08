package nanicky.losties.android.features.main.user

import android.content.Context
import kotlinx.coroutines.suspendCancellableCoroutine
import nanicky.losties.android.core.base.BaseViewModel
import nanicky.losties.android.core.data.local.AuthType
import nanicky.losties.android.core.data.local.UserRepository
import nanicky.losties.android.core.utils.FileUtils.AVATAR_FILENAME
import okhttp3.*
import timber.log.Timber
import java.io.IOException
import kotlin.coroutines.resume
import  nanicky.losties.android.core.base.Result
import nanicky.losties.android.core.utils.FileUtils
import nanicky.losties.android.core.utils.FileUtils.AVATAR_THUMBNAIL

class UserFragmentViewModel(
    private val context: Context,
    private val userRepository: UserRepository,
    private val httpClient: OkHttpClient
) : BaseViewModel<AuthType, String>() {

    fun signIn(
        userId: String,
        firstName: String,
        lastName: String,
        photo: String?,
        authType: AuthType
    ) {
        io {
            Timber.d("SignIn response successful. Getting avatar...")

            //пытаемся скачать аватарку из соцсети
            photo?.let {
                downloadAvatarByUrl(context, photo)
                FileUtils.thumbnail(context)
            }

            Timber.d("Saving user to prefs...")
            userRepository.saveUser(
                authType = authType,
                userId = userId,
                firstName = firstName,
                lastName = lastName
            )

            contentState.postValue(Result.Success(authType))
        }
    }

    suspend fun downloadAvatarByUrl(context: Context, url: String): Result<String, String> {
        val request: Request = Request.Builder().url(url).build()
        val call = httpClient.newCall(request)

        return suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation {
                call.cancel()
            }

            call.enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    processAvatarBody(context, response.body!!, AVATAR_FILENAME)
                    Timber.d("Avatar received... Writing to disk...")
                    continuation.resume(Result.Success("Downloaded"))
                }

                override fun onFailure(call: Call, e: IOException) {
                    Timber.d("Avatar receiving failed...")
                    continuation.resume(Result.Success("Error"))
                }

            })
        }

    }
}