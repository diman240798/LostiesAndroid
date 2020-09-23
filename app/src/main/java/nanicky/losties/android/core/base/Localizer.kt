package nanicky.losties.android.core.base

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import nanicky.losties.android.core.data.local.SettingsRepository
import java.util.*

class Localizer(
    private val context: Context,
    private val settingsRepository: SettingsRepository
) {

    private val ru =
        context.createConfigurationContext(
            Configuration(context.resources.configuration).apply {
                setLocale(Locale("ru", "RU"))
            }
        ).resources

    private val en =
        context.createConfigurationContext(
            Configuration(context.resources.configuration).apply {
                setLocale(Locale.ENGLISH)
            }
        ).resources

    fun tr(@StringRes id: Int) =
        if (settingsRepository.isRussianLocale) ru.getString(id) else en.getString(id)

}