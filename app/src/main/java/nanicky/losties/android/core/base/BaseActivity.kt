package nanicky.losties.android.core.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import nanicky.losties.android.core.data.local.*
import nanicky.losties.android.core.extensions.provideProgressDialog
import org.koin.android.ext.android.inject

abstract class BaseActivity: AppCompatActivity() {
    val progressDialog: AlertDialog by lazy { provideProgressDialog() }

    val firstTimeRunRepository: FirstTimeRunRepository by inject()
    val settingsRepository: SettingsRepository by inject()

    val userRepository: UserRepository by inject()

    val authNotifier: AuthNotifier by inject()

    val l: Localizer by inject()
}