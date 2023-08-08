package com.example.favouritegitrepo.data.remote

import com.example.favouritegitrepo.data.model.FavouriteRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("repos/{owner}/{repo}")
    suspend fun getRepoList(@Path("owner") owner : String, @Path("repo") repo : String) : FavouriteRepo
}