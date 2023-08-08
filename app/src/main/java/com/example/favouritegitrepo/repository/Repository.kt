package com.example.favouritegitrepo.repository

import com.example.favouritegitrepo.data.local.FavouriteRepoDao
import com.example.favouritegitrepo.data.remote.ApiService
import com.example.favouritegitrepo.data.model.FavouriteRepo
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: FavouriteRepoDao
) {
    suspend fun getRepoFromGithub(owner: String, repo: String) = apiService.getRepoList(owner, repo)

    suspend fun saveRepo(favouriteRepo: FavouriteRepo) = movieDao.saveRepo(favouriteRepo)

    suspend fun getAllRepoList() = movieDao.getRepoList()
}