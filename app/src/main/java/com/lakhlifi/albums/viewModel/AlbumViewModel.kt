package com.lakhlifi.albums.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lakhlifi.albums.network.model.Album
import com.lakhlifi.albums.repository.AlbumRepository

class AlbumViewModel: ViewModel() {
    private val repository=AlbumRepository()

    val albumList : LiveData<List<Album>>


    init {
        this.albumList = repository.albumList
    }

    fun getAlbums(application: Application){
        repository.getAlbums(application)
    }

    fun delete(context: Context, removedItem: Album) {
        repository.deleteAlbum(context, removedItem)
    }

}