package com.lakhlifi.albums.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lakhlifi.albums.database.AlbumDb
import com.lakhlifi.albums.model.Album
import kotlinx.coroutines.launch

class AlbumInfoViewModel(application: Application) : AndroidViewModel(application) {

    val album  = MutableLiveData<Album>()
    fun getAlbum(application: Application, id: Int) {
        viewModelScope.launch {
            val albumDb = AlbumDb.get(application).albumDao().getAlbum(id)
            album.value = albumDb
        }
    }
}