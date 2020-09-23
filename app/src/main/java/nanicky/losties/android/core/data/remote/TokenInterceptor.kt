package nanicky.losties.android.core.data.remote

import nanicky.losties.android.core.data.local.UserRepository
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val userRepository: UserRepository): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val tokenRequest = request.newBuilder()
            .header(
                "Authorization",
                Credentials.basic(userRepository.hashedId, userRepository.hashedPassword)
            ).build()
        return chain.proceed(tokenRequest)

    }
}