package nanicky.losties.android.core.data.local

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.MutableLiveData

class SettingsRepository(context: Context) {
    companion object {
        private const val SETTINGS_FILENAME = "settings_storage"

        private const val LANGUAGE_SETTINGS = "language_settings"
    }


    val languageChanged = MutableLiveData<Boolean>()

    private val prefs = context.getSharedPreferences(SETTINGS_FILENAME, Context.MODE_PRIVATE)


    var isRussianLocale: Boolean
        get() = prefs.getBoolean(LANGUAGE_SETTINGS,
            Resources.getSystem().configuration.locale.country == "RU")
        set(value)  {
            prefs.edit().putBoolean(LANGUAGE_SETTINGS, value).apply()
            languageChanged.postValue(value)
        }

}