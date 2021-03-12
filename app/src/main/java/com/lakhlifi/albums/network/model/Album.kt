package com.lakhlifi.albums.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Album(
    @PrimaryKey
    var id: Int,
    var title: String,
    var userId: Int
)