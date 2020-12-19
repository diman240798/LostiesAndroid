package nanicky.losties.android.features.model

import java.util.*

data class UserData(
        val id: UUID,
        var name: String,
        var numbers: String,
        var emails: String,
        var networksUrls: String
)
