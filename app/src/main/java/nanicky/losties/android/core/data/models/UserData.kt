package nanicky.losties.android.core.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class UserData(
        val id: UUID,
        var name: String,
        var numbers: String,
        var emails: String,
        var networksUrls: String
) : Parcelable
