package com.lakhlifi.albums.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.lakhlifi.albums.database.AlbumDb
import com.lakhlifi.albums.network.ALBUM_URL
import com.lakhlifi.albums.network.AlbumNetwork
import com.lakhlifi.albums.network.model.Album
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumRepository() {
    val albumList = MutableLiveData<List<Album>>()
     suspend fun getAlbums(application: Context) {

        val db = AlbumDb.get(application)
        val albumDao = db.albumDao()
        val albums = albumDao.getAllAlbums()

        if (albums.size > 0) {
            Log.d("ALBUMS", "FROM DB${Gson().toJson(albums)}")
            albumList.value = albums
            return
        }else{
            val retrofit =
                Retrofit.Builder().baseUrl(ALBUM_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service = retrofit.create(AlbumNetwork::class.java)

            service.getAlbums().enqueue(object : Callback<List<Album>> {
                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    Toast.makeText(application, "error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<List<Album>>,
                    response: Response<List<Album>>
                ) {
                    Log.d("ALBUMS", "FROM API")
                    Log.d("ALbumRepository", "Response: ${Gson().toJson(response.body())}")
                    albumList.value = response.body()
                    GlobalScope.launch { albumDao.insertAlbum(albumList.value!!) }

                }
            })
        }
    }


    suspend fun deleteAlbum(context: Context, id:Album) {
        val db = AlbumDb.get(context)
        val albumDao = db.albumDao()
        albumDao.deleteAlbum(id)
    }

    suspend fun insert(context: Context, album: Album) {
        val db = AlbumDb.get(context)
        val albumDao = db.albumDao()
        albumDao.insertAlbum(album)
    }

}