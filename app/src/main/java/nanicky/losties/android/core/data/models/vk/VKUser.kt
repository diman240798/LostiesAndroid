package nanicky.losties.android.core.data.models.vk

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class VKUser(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val photo: String = "",
    val deactivated: Boolean = false) : Parcelable

fun parseUser(json: JSONObject)
        = VKUser(
    id = json.optInt("id", 0),
    firstName = json.optString("first_name", ""),
    lastName = json.optString("last_name", ""),
    photo = json.optString("photo_200", ""),
    deactivated = json.optBoolean("deactivated", false)
)

