package com.lakhlifi.albums.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lakhlifi.albums.R
import com.lakhlifi.albums.adapter.AlbumAdapter
import com.lakhlifi.albums.viewModel.AlbumViewModel

class AlbumActivity : AppCompatActivity() {
    lateinit var rvAlbums : RecyclerView

    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var adapter: AlbumAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        rvAlbums = findViewById(R.id.rv_album)
        adapter = AlbumAdapter( this)
        itemTouchHelper.attachToRecyclerView(rvAlbums)
        rvAlbums.adapter = adapter

        albumViewModel= ViewModelProvider(this).get(AlbumViewModel::class.java)
        albumViewModel.getAlbums(application)

        albumViewModel.albumList.observe(this, Observer {
            adapter.setAlbumList(it)

        })
        rvAlbums.layoutManager = LinearLayoutManager(this)

    }

    var itemTouchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.UP or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT or ItemTouchHelper.DOWN

    ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                albumViewModel.albumList.removeObserver { it.first { it.id==1 } }
                adapter.notifyDataSetChanged()
            }
        })

}