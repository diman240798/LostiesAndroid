package nanicky.losties.android.core.data.local

import android.content.Context

class FirstTimeRunRepository(context: Context) {
    companion object {
        private const val STORAGE_FILENAME = "first_time_run_storage"
        private const val FIRST_TIME_RUN = "first_time_run"
    }

    private val prefs = context.getSharedPreferences(STORAGE_FILENAME, Context.MODE_PRIVATE)

    private var firsTimeRun: Boolean
        get() = prefs.getBoolean(FIRST_TIME_RUN, false)
        set(value) = prefs.edit().putBoolean(FIRST_TIME_RUN, value).apply()

    fun isFirstTimeRun() = !firsTimeRun

    fun updateFirstTimeRun(flag: Boolean) {
        firsTimeRun = flag
    }
}