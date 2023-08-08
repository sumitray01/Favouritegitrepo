package com.example.favouritegitrepo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_repo")
data class FavouriteRepo(
    val name : String?,
    val description : String?,
    val html_url : String?,
    val message : String?
){
    @PrimaryKey (autoGenerate = true)
    var id : Int = 0
}
