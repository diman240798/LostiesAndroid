package nanicky.losties.android.features.watchpublicationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nanicky.losties.android.core.base.BaseViewModel
import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.android.core.data.remote.AnimalRemoteCrudRepository

class WatchPublicationListActivityViewModel(
    val repo: AnimalRemoteCrudRepository
) : BaseViewModel<String, String>() {

    private val _animals = MutableLiveData<List<AnimalPublication>>()
    val animals: LiveData<List<AnimalPublication>> = _animals

    fun getLostAnimals(userId: String? = null) {
        io {
            val response = repo.getLostAnimals(userId)
            if (response.isSuccessful) {
                val animals = response.body()
                _animals.postValue(animals)
            } else {

            }
        }
    }

    fun getSeenAnimals(userId: String? = null) {
        io {
            val response = repo.getSeenAnimals(userId)
            if (response.isSuccessful) {
                val animals = response.body()
                _animals.postValue(animals)
            } else {

            }
        }
    }

    fun getTakenAnimals(userId: String? = null) {
        io {
            val response = repo.getTakenAnimals(userId)
            if (response.isSuccessful) {
                val animals = response.body()
                _animals.postValue(animals)
            } else {

            }
        }
    }
}
