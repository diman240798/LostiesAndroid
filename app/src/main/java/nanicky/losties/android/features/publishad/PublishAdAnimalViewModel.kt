package nanicky.losties.android.features.publishad

import nanicky.losties.android.core.base.BaseViewModel
import nanicky.losties.android.features.common.requests.AddAnimalRequest

class PublishAdAnimalViewModel(
    val repo: PublishAdAnimalRepository
) : BaseViewModel<String, String>() {
    fun addLostAnimal(addAnimalRequest: AddAnimalRequest) {
        io {
            repo.addLostAnimal(addAnimalRequest)
        }
    }

    fun addFoundAnimal(addAnimalRequest: AddAnimalRequest) {
        io {
            repo.addFoundAnimal(addAnimalRequest)
        }
    }

    fun addSeenAnimal(addAnimalRequest: AddAnimalRequest) {
        io {
            repo.addSeenAnimal(addAnimalRequest)
        }
    }

}
