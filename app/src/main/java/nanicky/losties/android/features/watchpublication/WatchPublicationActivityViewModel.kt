package nanicky.losties.android.features.watchpublication

import nanicky.losties.android.core.base.BaseViewModel
import nanicky.losties.android.core.data.remote.Api
import nanicky.losties.android.features.enums.PublicationTypes
import java.util.*

class WatchPublicationActivityViewModel(private val api: Api) : BaseViewModel<String?, String?>() {

    fun publishOnSocailNetworks(publicationId: UUID, publicationTypes: PublicationTypes) {
        io {
            api.publishOnSocialNetworks(publicationId, publicationTypes)
        }
    }
}