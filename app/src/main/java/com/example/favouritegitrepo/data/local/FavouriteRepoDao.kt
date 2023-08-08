package com.example.favouritegitrepo.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.favouritegitrepo.data.model.FavouriteRepo

@Dao
interface FavouriteRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRepo(favouriteRepo: FavouriteRepo)

    @Query("SELECT * FROM favourite_repo")
    suspend fun getRepoList() : List<FavouriteRepo>
}