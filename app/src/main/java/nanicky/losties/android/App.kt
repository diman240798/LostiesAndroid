package nanicky.losties.android

import nanicky.losties.android.core.di.localModule
import nanicky.losties.android.core.di.remoteModule
import nanicky.losties.android.core.di.viewModelModule
import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        //NotificationHelper.initNotificationChanel(this)
        AndroidThreeTen.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                listOf(
                    localModule(),
                    remoteModule(),
                    viewModelModule()
                )
            )
        }

        FacebookSdk.sdkInitialize(applicationContext)
//        AppEventsLogger.activateApp(this@App)
    }


}