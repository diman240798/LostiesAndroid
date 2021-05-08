package nanicky.losties.android.features.main.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nanicky.losties.android.core.base.BaseViewModel
import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.android.core.data.models.AnimalPublicationTyped
import nanicky.losties.android.core.data.remote.AnimalRemoteCrudRepository

class FeedFragmentViewModel(
    private val repo: AnimalRemoteCrudRepository
) : BaseViewModel<String, String>() {

    private val _newPublications = MutableLiveData<List<AnimalPublicationTyped>>()
    val newPublications: LiveData<List<AnimalPublicationTyped>> = _newPublications


    fun getNewPublications() {
        io {
            val response = repo.getNewPublications()
            if (response.isSuccessful) {
                val animals = response.body()
                _newPublications.postValue(animals)
            } else {

            }
        }
    }
}