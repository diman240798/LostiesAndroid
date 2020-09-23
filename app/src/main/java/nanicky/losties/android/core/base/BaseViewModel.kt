package nanicky.losties.android.core.base

import nanicky.losties.android.core.utils.FileUtils
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

abstract class BaseViewModel<S, E> : ViewModel() {

    companion object {
        fun processAvatarBody(
            context: Context,
            responseBody: ResponseBody,
            outputFileName: String): Boolean {

            return if (responseBody.contentLength() == 0L) {
                false
            } else {
                FileUtils.writeToDisk(context, responseBody, outputFileName)
            }
        }
    }


    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _errorState = SingleLiveEvent<Throwable>()
    val errorState: LiveData<Throwable> = _errorState

    val contentState = MutableLiveData<Result<S,E>>()

    protected fun io(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _loadingState.value = true
                block()
            } catch (error: Exception) {
                _errorState.value = error
            } finally {
                _loadingState.value = false
            }
        }
    }

    protected fun ioWithCallbacks(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                onStart()
                block()
            } catch (error: Exception) {
                _errorState.value = error
            } finally {
                onFinish()
            }
        }
    }

    fun init(
        owner: LifecycleOwner,
        loading: (Boolean) -> Unit = {},
        error: (Throwable) -> Unit = {},
        result: (Result<S,E>) -> Unit = {}) {

        loadingState.observe(owner, Observer {
            loading(it)
        })

        errorState.observe(owner, Observer {
            error(it)
        })

        contentState.observe(owner, Observer {
            result(it)
        })
    }




}