package com.example.favouritegitrepo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.favouritegitrepo.data.model.FavouriteRepo

@Database(entities = [FavouriteRepo::class], version = 1, exportSchema = false)
abstract class RepoDatabase  : RoomDatabase(){
    abstract fun getFavouriteRepoDao() : FavouriteRepoDao
}