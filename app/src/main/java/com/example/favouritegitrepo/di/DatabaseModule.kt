package com.example.favouritegitrepo.di

import android.content.Context
import androidx.room.Room
import com.example.favouritegitrepo.data.local.FavouriteRepoDao
import com.example.favouritegitrepo.data.local.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideMovieDao(movieDatabase: RepoDatabase) : FavouriteRepoDao{
        return movieDatabase.getFavouriteRepoDao()
    }

    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context) : RepoDatabase{
        return Room.databaseBuilder(context,RepoDatabase::class.java,"MovieDatabase").build()
    }
}