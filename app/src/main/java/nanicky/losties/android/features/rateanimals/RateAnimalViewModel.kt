package nanicky.losties.android.features.rateanimals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nanicky.losties.android.core.base.BaseViewModel
import nanicky.losties.losties.util.AnimalType

class RateAnimalViewModel(
    private val repo: RateAnimalRepository
) : BaseViewModel<String, String>() {

    var type: AnimalType = AnimalType.ALL

    private val _animalRandomRequest: MutableLiveData<AnimalRandomRequest> = MutableLiveData()
    val animalRandomRequest: LiveData<AnimalRandomRequest> = _animalRandomRequest

    fun requestRandomAnimal() {
        io {
            val animalRandomRequest = repo.getRandomAnimal()
            _animalRandomRequest.postValue(animalRandomRequest)
        }
    }

    fun requestRandomCat() {
        io {
            val animalRandomRequest = repo.getRandomCat()
            _animalRandomRequest.postValue(animalRandomRequest)
        }
    }

    fun requestRandomDog() {
        io {
            val animalRandomRequest = repo.getRandomDog()
            _animalRandomRequest.postValue(animalRandomRequest)
        }
    }

    fun rateAnimal(liked: Boolean = false) {
        io {
            _animalRandomRequest.value?.let {
                repo.rateAnimal(it.name_photo, liked)
            }
        }
    }

}