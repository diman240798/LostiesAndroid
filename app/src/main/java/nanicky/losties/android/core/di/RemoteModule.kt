package nanicky.losties.android.core.di

import nanicky.losties.android.BuildConfig
import nanicky.losties.android.core.data.local.UserRepository
import nanicky.losties.android.core.data.remote.Api
import nanicky.losties.android.core.data.remote.TokenInterceptor
import nanicky.losties.android.core.extensions.decodeCertificatePem
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.tls.HandshakeCertificates
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier

const val ENTRY_POINT = "https://localhost:9000"

const val DEFAULT_HTTP_CLIENT = "default_http_client"
const val TOKEN_HTTP_CLIENT = "token_http_client"

const val SOCIAL_NETWORKS_HTTP_CLIENT = "social_networks_http_client"

const val DEFAULT_RETROFIT_CLIENT = "default_retrofit_client"
const val TOKEN_RETROFIT_CLIENT = "token_retrofit_client"

fun remoteModule() = module {

    single(named(SOCIAL_NETWORKS_HTTP_CLIENT)) {
        createSocialNetworksOkHttpClient()
    }

    single(named(DEFAULT_HTTP_CLIENT)) {
        createOkHttpClient()
    }

    single(named(TOKEN_HTTP_CLIENT)) {
        createTokenOkHttpClient(get())
    }


    single(named(DEFAULT_RETROFIT_CLIENT)) {
        createApi<Api>(get(named(DEFAULT_HTTP_CLIENT)))
    }

    single(named(TOKEN_RETROFIT_CLIENT)) {
        createApi<Api>(get(named(TOKEN_HTTP_CLIENT)))
    }



}

fun createSocialNetworksOkHttpClient(): OkHttpClient {
    return addLoggingInterceptorIfDebug(OkHttpClient.Builder())
        .build()
}

fun createOkHttpClient(): OkHttpClient {

    val certificates = HandshakeCertificates.Builder()
        .build()

    return addLoggingInterceptorIfDebug(OkHttpClient.Builder())
        .sslSocketFactory(certificates.sslSocketFactory(), certificates.trustManager)
        .hostnameVerifier(HostnameVerifier { _, _ -> true })
        .build()

}

fun createTokenOkHttpClient(userRepository: UserRepository): OkHttpClient {

    val certificates = HandshakeCertificates.Builder()
        .build()

    return addLoggingInterceptorIfDebug(OkHttpClient.Builder())
        .addInterceptor(TokenInterceptor(userRepository))
        .sslSocketFactory(certificates.sslSocketFactory(), certificates.trustManager)
        .hostnameVerifier(HostnameVerifier { _, _ -> true })
        .build()
}

fun addLoggingInterceptorIfDebug(builder: OkHttpClient.Builder): OkHttpClient.Builder {
    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return builder.addInterceptor(httpLoggingInterceptor)
    }
    return builder
}




inline fun <reified T> createApi(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(ENTRY_POINT)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    return retrofit.create(T::class.java)
}
