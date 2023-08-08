package com.example.favouritegitrepo.ui.favouriterepo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.favouritegitrepo.data.remote.NetworkResult
import com.example.favouritegitrepo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import com.example.favouritegitrepo.data.model.FavouriteRepo
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _response = MutableLiveData<NetworkResult<FavouriteRepo>>()
    val response: LiveData<NetworkResult<FavouriteRepo>>
        get() = _response

    private var _repoList = MutableLiveData<List<FavouriteRepo>>()
    val repoList: LiveData<List<FavouriteRepo>>
        get() = _repoList

    private var job: Job? = null

    init {
        getAllReposFromDb()
    }

    fun getRepo(owner: String, repo: String) {
        _response.postValue(NetworkResult.LOADING())
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getRepoFromGithub(owner, repo)
                if (response.name.isNullOrEmpty().not()) {
                    _response.postValue(NetworkResult.SUCCESS(response))
                    saveToDB(response)
                } else {
                    _response.postValue(NetworkResult.FAILURE(message = response.message))
                }
            } catch (e: java.lang.Exception) {
                _response.postValue(NetworkResult.FAILURE(message = "some exception occurred"))

            }

        }

    }

     fun getAllReposFromDb() {
        CoroutineScope(Dispatchers.IO).launch {
           _repoList.postValue(repository.getAllRepoList())
        }
    }

    private fun saveToDB(favouriteRepo: FavouriteRepo) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveRepo(favouriteRepo)
        }
    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}

