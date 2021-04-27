package com.lakhlifi.albums.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Album(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var userId: Int
){

        constructor( title: String, userId: Int) : this(0,  title, userId)

}