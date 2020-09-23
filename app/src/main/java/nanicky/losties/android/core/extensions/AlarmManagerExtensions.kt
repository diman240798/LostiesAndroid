package nanicky.losties.android.core.extensions

import android.app.AlarmManager
import android.app.PendingIntent
import android.os.Build

fun AlarmManager.setExactVersion(type: Int, triggerAtMillis: Long, operation: PendingIntent) {
    if (Build.VERSION.SDK_INT >= 23) {
        setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, operation)
    } else {
        setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, operation)
    }
}