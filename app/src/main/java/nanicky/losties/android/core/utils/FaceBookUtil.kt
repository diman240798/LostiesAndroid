package nanicky.losties.android.core.utils

import com.facebook.AccessToken
import com.facebook.GraphRequest
import nanicky.losties.android.core.data.local.AuthNotifier
import nanicky.losties.android.core.data.models.facebook.FacebookUser
import timber.log.Timber
import java.lang.Exception

class FaceBookUtil {
companion object {
    fun getFacebookUserOrNull(
        accessToken: AccessToken,
        authNotifier: AuthNotifier
    ) {
        Timber.d("getFacebookUserOrNull...")

        GraphRequest.newMeRequest(accessToken) { _ , response -> // Application code
            try {
                if (response.error != null) {
                    throw response.error.exception
                }
                Timber.d(response.toString())

                val facebookUser = FacebookUser.make(accessToken.userId, accessToken, response.jsonObject)
                Timber.d(facebookUser.toString())

                facebookUser.let {
                    Timber.d("authNotifier.facebookUid.value = $facebookUser")
                    authNotifier.facebookUid.value = facebookUser
                }

            } catch (e: Exception) {
                Timber.d("Error getting user info")
            }
        }.executeAsync()

    }
}
}
