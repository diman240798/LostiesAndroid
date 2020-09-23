package nanicky.losties.android.core.data.local

import androidx.lifecycle.MutableLiveData
import org.joda.time.DateTime

class CalendarNotifier {
    val selectedMonthEvent = MutableLiveData<DateTime>()
}