package com.lakhlifi.albums.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lakhlifi.albums.R
import com.lakhlifi.albums.adapter.AlbumAdapter
import com.lakhlifi.albums.network.model.Album
import com.lakhlifi.albums.viewModel.AlbumViewModel

class AlbumActivity : AppCompatActivity() {

    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)





        val rvUsers : RecyclerView = findViewById(R.id.rv_album)
        adapter = AlbumAdapter( this)
        rvUsers.adapter = adapter

        albumViewModel= ViewModelProvider(this).get(AlbumViewModel::class.java)
        albumViewModel.getAlbums(application)

        albumViewModel.albumList.observe(this, Observer {
            adapter.setAlbumList(it)

        })
        rvUsers.layoutManager = LinearLayoutManager(this)

    }

}