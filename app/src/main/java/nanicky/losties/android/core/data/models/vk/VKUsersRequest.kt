package nanicky.losties.android.core.data.models.vk

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKUsersRequest: VKRequest<List<VKUser>> {
    constructor(uid: Int): super("users.get") {
        addParam("user_ids", uid.toString())
        addParam("fields", "photo_200")
    }

    override fun parse(r: JSONObject): List<VKUser> {
        val users = r.getJSONArray("response")
        val result = ArrayList<VKUser>()
        for (i in 0 until users.length()) {
            result.add(
                parseUser(
                    users.getJSONObject(i)
                )
            )
        }
        return result
    }
}