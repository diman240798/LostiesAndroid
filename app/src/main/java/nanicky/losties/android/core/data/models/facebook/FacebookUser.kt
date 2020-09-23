package nanicky.losties.android.core.data.models.facebook

import android.os.Parcelable
import com.facebook.AccessToken
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class FacebookUser(
    val id: String = "0",
    val firstName: String = "",
    val lastName: String = "",
    val photo: String? = null
) : Parcelable {

    companion object {
        fun make(userId: String, accessToken: AccessToken, json: JSONObject): FacebookUser {
            var firstName = json.optString("first_name", "")
            var lastName = ""
            if (firstName.isEmpty()) {
                firstName = json.optString("name", "")
            } else {
                lastName = json.optString("last_name", "")
            }
            val photo = "https://graph.facebook.com/${userId}/picture?type=normal&access_token=${accessToken.token}"
            return FacebookUser(userId, firstName, lastName, photo)
        }
    }
}