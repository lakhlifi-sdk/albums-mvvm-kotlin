package com.lakhlifi.albums.database

import android.app.Application
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
        fun get(application: Application):AlbumDb{
            if (albumDb==null)
            albumDb= Room.databaseBuilder(application,AlbumDb::class.java, "albums").allowMainThreadQueries().build()
            return albumDb!!
        }
    }
}