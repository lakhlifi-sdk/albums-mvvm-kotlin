package com.lakhlifi.albums.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lakhlifi.albums.R
import com.lakhlifi.albums.network.model.Album
import com.lakhlifi.albums.viewModel.AlbumInfoViewModel
import com.lakhlifi.albums.viewModel.AlbumViewModel
import com.squareup.picasso.Picasso


class AlbumInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_info)

        //views
        val album_title=findViewById<TextView>(R.id.album_title)
        val image_info=findViewById<ImageView>(R.id.image_info)
        val id_album=findViewById<TextView>(R.id.id_Album)
        val id_user=findViewById<TextView>(R.id.id_user)


        val extras = intent.extras ?: return
        val id = extras.getInt("id")
        var albumInfoViewModel: AlbumInfoViewModel = ViewModelProvider(this).get(AlbumInfoViewModel::class.java)
        //filter using the method getAlbum
        albumInfoViewModel.getAlbum(application,id)
        albumInfoViewModel.album.observe(this, Observer{
            album_title.setText(it.title)
           Picasso.get().load("https://picsum.photos/id/${it.id}/200").into(image_info);
            id_album.setText(""+it.id)
            id_user.text=""+it.userId

        })
}
}