package com.lakhlifi.albums.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lakhlifi.albums.database.AlbumDb
import com.lakhlifi.albums.network.model.Album
import com.lakhlifi.albums.repository.AlbumRepository
import kotlinx.coroutines.launch

class AlbumViewModel: ViewModel() {
    private val repository=AlbumRepository()

    val albumList : LiveData<List<Album>>


    init {
        this.albumList = repository.albumList
    }


    fun getAlbums(application: Application){
        viewModelScope.launch {
            repository.getAlbums(application)
        }

    }

    fun delete(context: Context, removedItem: Album) {
        viewModelScope.launch {
            repository.deleteAlbum(context, removedItem)
        }

    }

    fun insert(context: Context, album: Album) {
        viewModelScope.launch {
            repository.insert(context,album)
        }

    }

}