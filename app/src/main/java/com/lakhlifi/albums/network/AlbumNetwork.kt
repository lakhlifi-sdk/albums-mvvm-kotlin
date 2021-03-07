package com.lakhlifi.albums.network

import com.lakhlifi.albums.network.model.Album
import retrofit2.Call
import retrofit2.http.GET

const val ALBUM_URL="https://jsonplaceholder.typicode.com/"
interface AlbumNetwork {
    @GET("albums")
    fun getAlbums(): Call<List<Album>>
}