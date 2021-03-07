package com.lakhlifi.albums.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lakhlifi.albums.network.model.Album
import com.lakhlifi.albums.repository.AlbumRepository

class AlbumViewModel(application: Application):AndroidViewModel( application) {
    private val repository=AlbumRepository(application)

    val albumList : LiveData<List<Album>>

    init {
        this.albumList = repository.albumList
    }

    fun getAlbums(){
        repository.getAlbums()
    }

}