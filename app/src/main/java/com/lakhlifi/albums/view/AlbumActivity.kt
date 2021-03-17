package com.lakhlifi.albums.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lakhlifi.albums.R
import com.lakhlifi.albums.adapter.AlbumAdapter
import com.lakhlifi.albums.network.model.Album
import com.lakhlifi.albums.viewModel.AlbumViewModel
import kotlin.random.Random

class AlbumActivity : AppCompatActivity() {
    lateinit var rvAlbums: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var btn_add_item: Button
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var adapter: AlbumAdapter
    private lateinit var empty_img: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        rvAlbums = findViewById(R.id.rv_album)
        swipeRefreshLayout = findViewById(R.id.swip_refresh_layout)
        empty_img = findViewById(R.id.empty_img)
        adapter = AlbumAdapter(this)
        itemTouchHelper.attachToRecyclerView(rvAlbums)
        rvAlbums.adapter = adapter

        //button intialisation
        btn_add_item = findViewById(R.id.btn_add_item)

        //empty_img=findViewById(R.id.empty_img)
        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        //albumViewModel.getAlbums(application)
        albumViewModel.albumList.observe(this, Observer {

            if(it.isNotEmpty()){
                btn_add_item.visibility=View.VISIBLE
                rvAlbums.visibility=View.VISIBLE
                empty_img.visibility = View.GONE

            }else{
                empty_img.visibility = View.VISIBLE
                rvAlbums.visibility=View.GONE
                btn_add_item.visibility=View.GONE
            }
            adapter.setAlbumList(it)

        })
        rvAlbums.layoutManager = LinearLayoutManager(this)


        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
            albumViewModel.getAlbums(application)
            swipeRefreshLayout.isRefreshing = false
        })

        //add an album to database
        btn_add_item.setOnClickListener(View.OnClickListener {
            val random = Random.nextInt(120, 1000)
            Log.d("random", "" + random)
            var album = Album(random, "album added " + random, 12)
            albumViewModel.insert(this, album)
            Toast.makeText(this, "item inserted", Toast.LENGTH_LONG).show()
            Log.d("tag", "item inserted")
            adapter.addItem(1, album)
        })
    }

    var itemTouchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT

        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val removedItem = adapter.removeItem(position)
                //adapter.notifyItemRemoved(viewHolder.adapterPosition)
                val snackbar = Snackbar
                    .make(
                        swipeRefreshLayout,
                        "Item was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )

                snackbar.setAction("DISMISS", View.OnClickListener {
                    // executed when DISMISS is clicked
                    adapter.restoreItem(position, removedItem);
                    System.out.println("Snackbar Set Action - OnClick.")
                })
                snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    override fun onShown(transientBottomBar: Snackbar?) {
                        super.onShown(transientBottomBar)
                    }

                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT)
                            albumViewModel.delete(this@AlbumActivity, removedItem)
                        Toast.makeText(this@AlbumActivity, "item deleted", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
                snackbar.show()
            }
        })
}