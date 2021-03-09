package com.lakhlifi.albums.dao

import androidx.room.*
import com.lakhlifi.albums.network.model.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAlbum(List: List<Album>)

    @Delete
    fun delete(album: Album)

    @Query("SELECT * FROM album WHERE id = :id" )
    fun getAlbum(id: Int) : Album

    @Query("SELECT * FROM album")
    fun getAllAlbums(): List<Album>


}