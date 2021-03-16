package com.lakhlifi.albums.dao

import androidx.room.*
import com.lakhlifi.albums.network.model.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insertAlbum(List: List<Album>)

    @Delete
    suspend fun deleteAlbum(album: Album) : Int

    @Query("SELECT * FROM album WHERE id = :id" )
    suspend fun getAlbum(id: Int) : Album

    @Query("SELECT * FROM album")
    suspend fun getAllAlbums(): List<Album>

    @Insert
    suspend fun insertAlbum(album: Album) : Long

    @Query("UPDATE album SET title = :title ,userId= :userId WHERE id LIKE :id ")
    fun updateAlbum(id: Int, title: String, userId: Int): Int

    @Query("SELECT * FROM Album WHERE id= :id")
    fun find(id: Int): Int

    @Query("SELECT * FROM album")
    fun read():Int

}