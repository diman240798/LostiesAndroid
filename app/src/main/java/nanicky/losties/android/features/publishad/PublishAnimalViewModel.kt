package nanicky.losties.android.features.publishad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nanicky.losties.android.core.base.BaseViewModel
import nanicky.losties.android.core.data.remote.AnimalRemoteCrudRepository
import nanicky.losties.android.features.common.requests.AddAnimalRequest
import nanicky.losties.losties.util.AnimalType
import java.io.File

class PublishAnimalViewModel(
    private val repo: AnimalRemoteCrudRepository
) : BaseViewModel<String, String>() {

    private val _animalType: MutableLiveData<AnimalType> = MutableLiveData(AnimalType.CAT)
    val animalType: LiveData<AnimalType> = _animalType

    fun addLostAnimal(addAnimalRequest: AddAnimalRequest) {
        io {
            repo.addLostAnimal(addAnimalRequest)
        }
    }

    fun addTakenAnimal(addAnimalRequest: AddAnimalRequest) {
        io {
            repo.addTakenAnimal(addAnimalRequest)
        }
    }

    fun addSeenAnimal(addAnimalRequest: AddAnimalRequest) {
        io {
            repo.addSeenAnimal(addAnimalRequest)
        }
    }

    fun requestAnimalTypeByPhoto(imageFile: File, onFinish: () -> Unit) {
        ioWithCallbacks(
            {},
            onFinish,
            {
                val animalType: String = repo.requestAnimalTypeByPhoto(imageFile)
                _animalType.value = when (animalType) {
                    "cat" -> AnimalType.CAT
                    "dog" -> AnimalType.DOG
                    else -> AnimalType.OTHER
                }
            })
    }

}
