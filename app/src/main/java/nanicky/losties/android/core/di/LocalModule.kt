package nanicky.losties.android.core.di

import nanicky.losties.android.core.base.Localizer
import nanicky.losties.android.core.data.local.AuthNotifier
import nanicky.losties.android.core.data.local.FirstTimeRunRepository
import nanicky.losties.android.core.data.local.SettingsRepository
import nanicky.losties.android.core.data.local.UserRepository
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
        AuthNotifier()
    }

    single {
        Localizer(get(), get())
    }

}