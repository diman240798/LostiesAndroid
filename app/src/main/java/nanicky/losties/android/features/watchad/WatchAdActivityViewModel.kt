package nanicky.losties.android.features.watchad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nanicky.losties.android.core.base.BaseViewModel
import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.android.core.data.remote.AnimalRemoteCrudRepository

class WatchAdActivityViewModel(
    val repo: AnimalRemoteCrudRepository
) : BaseViewModel<String, String>() {

    private val _animals = MutableLiveData<List<AnimalPublication>>()
    val animals: LiveData<List<AnimalPublication>> = _animals

    fun getLostAnimals() {
        io {
            val response = repo.getLostAnimals()
            if (response.isSuccessful) {
                val animals = response.body()
                _animals.postValue(animals)
            } else {

            }
        }
    }

    fun getSeenAnimals() {
        io {
            val response = repo.getSeenAnimals()
            if (response.isSuccessful) {
                val animals = response.body()
                _animals.postValue(animals)
            } else {

            }
        }
    }

    fun getFoundAnimals() {
        io {
            val response = repo.getFoundAnimals()
            if (response.isSuccessful) {
                val animals = response.body()
                _animals.postValue(animals)
            } else {

            }
        }
    }
}
