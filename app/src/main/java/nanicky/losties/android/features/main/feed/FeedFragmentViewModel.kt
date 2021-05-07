package nanicky.losties.android.features.main.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nanicky.losties.android.core.base.BaseViewModel
import nanicky.losties.android.core.data.remote.AnimalRemoteCrudRepository

class FeedFragmentViewModel(
    private val repo: AnimalRemoteCrudRepository
) : BaseViewModel<String, String>() {

//    private val _newPublications = MutableLiveData<Publication>()
//    val newPublications: LiveData<Publication> = _newPublications


//    fun getNewPublications() {
//        io {
//            val newPublications = repo.getNewPublications()
//            _newPublications.postValue(newPublications)
//        }
//    }
}