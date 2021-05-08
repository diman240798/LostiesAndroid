package nanicky.losties.android.core.data.local

import nanicky.losties.android.core.extensions.md5
import android.content.Context

class UserRepository(context: Context) {
    companion object {
        private const val STORAGE_FILENAME = "user_storage"

        private const val USER_FIRST_NAME = "user_first_name"
        private const val USER_LAST_NAME = "user_last_name"

        private const val USER_VK_ID = "user_vk_id"
        private const val USER_FACEBOOK_ID = "user_facebook_id"
    }

    private val prefs = context.getSharedPreferences(STORAGE_FILENAME, Context.MODE_PRIVATE)

    var firstName: String
        get() = prefs.getString(USER_FIRST_NAME, "") ?: ""
        set(value) = prefs.edit().putString(USER_FIRST_NAME, value).apply()

    var lastName: String
        get() = prefs.getString(USER_LAST_NAME, "") ?: ""
        set(value) = prefs.edit().putString(USER_LAST_NAME, value).apply()

    var vkId: String
        get() = prefs.getString(USER_VK_ID, "") ?: ""
        set(value) = prefs.edit().putString(USER_VK_ID, value).apply()

    var facebookId: String
        get() = prefs.getString(USER_FACEBOOK_ID, "") ?: ""
        set(value) = prefs.edit().putString(USER_FACEBOOK_ID, value).apply()

    val hashedId: String
        get() {
            return if (vkId.isNotEmpty()) {
                "${AuthType.vk}-$vkId-user".md5()
            } else {
                "${AuthType.facebook}-$facebookId-user".md5()
            }
        }

    val hashedPassword: String
        get() {
            return if (vkId.isNotEmpty()) {
                "$vkId-password".md5()
            } else {
                "$facebookId-password".md5()
            }
        }

    fun fullName() = "$firstName $lastName"

    fun isAuthorized(): Boolean = vkId.isNotEmpty() || facebookId.isNotEmpty()

    fun saveUser(authType: AuthType, userId: String, firstName: String, lastName: String) {

        this.firstName = firstName
        this.lastName = lastName

        if (authType == AuthType.vk) {
            this.vkId = userId
        } else {
            this.facebookId = userId
        }
    }

    fun logout() {
        firstName = ""
        lastName = ""
        vkId = ""
        facebookId = ""
    }

    fun getId(): String? =
        if (isAuthorized()) {
            if (facebookId.isNotEmpty()) {
                facebookId
            } else {
                vkId
            }
        } else null

}