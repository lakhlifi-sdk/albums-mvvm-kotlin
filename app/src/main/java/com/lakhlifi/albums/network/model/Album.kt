package com.lakhlifi.albums.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.internal.PrepareOp

@Entity
data class Album(
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int
)