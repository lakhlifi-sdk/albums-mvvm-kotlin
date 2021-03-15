package com.lakhlifi.albums.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lakhlifi.albums.dao.AlbumDao
import com.lakhlifi.albums.network.model.Album

@Database(entities=[Album::class], version=1)
abstract class AlbumDb:RoomDatabase() {
    abstract fun albumDao() : AlbumDao
    companion object{
        var albumDb:AlbumDb?=null
        fun get(application: Context):AlbumDb{
            if (albumDb==null)
                //albumDb= Room.databaseBuilder(application,AlbumDb::class.java, "albums").allowMainThreadQueries().build()
                //we use allowMainThreadQueries when we din't use coroutine (viewModelScope)
                albumDb= Room.databaseBuilder(application,AlbumDb::class.java, "albums").build()
                return albumDb!!
        }
    }
}