package nanicky.losties.android.core.data.local

import nanicky.losties.android.core.base.SingleLiveEvent
import nanicky.losties.android.core.data.models.facebook.FacebookUser
import nanicky.losties.android.core.data.models.vk.VKUser

class AuthNotifier {
    val vkUid = SingleLiveEvent<VKUser>()
    val facebookUid = SingleLiveEvent<FacebookUser>()
    val loggedIn = SingleLiveEvent<Boolean>()
}