package nanicky.losties.android.core.di

import nanicky.losties.android.core.data.local.*
import androidx.room.Room
import nanicky.losties.android.core.base.Localizer
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val DB_NAME = "calendar.db"

fun localModule() = module {

    /*single {
        Room
            .databaseBuilder(androidApplication(), AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }*/

    single {
        FirstTimeRunRepository(get())
    }

    single {
        UserRepository(get())
    }

    single {
        SettingsRepository(get())
    }

    single {
        CalendarNotifier()
    }

    single {
        AuthNotifier()
    }

    single {
        Localizer(get(), get())
    }

}