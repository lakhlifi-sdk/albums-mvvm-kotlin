package com.lakhlifi.albums.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
//import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lakhlifi.albums.R
//import com.lakhlifi.albums.databinding.ActivityAlbumInfoBinding
import com.lakhlifi.albums.network.model.Album
import com.lakhlifi.albums.viewModel.AlbumInfoViewModel
import com.lakhlifi.albums.viewModel.AlbumViewModel
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


class AlbumInfo : AppCompatActivity() {
    //lateinit var binding:ActivityAlbumInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //get setContentView from DataBindingUtil
        //binding= DataBindingUtil.setContentView(this,R.layout.activity_album_info)
        setContentView(R.layout.activity_album_info)

        //views with binding
        /*val album_title_binding=binding.albumTitle
        val album_image_binding=binding.imageInfo
        val id_user_binding=binding.idUser
        val id_album_binding=binding.idAlbum*/

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
           Picasso
               .get()
               .load("https://picsum.photos/id/${it.id}/200")
               .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
               .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
               .placeholder(R.drawable.image)
               .into(image_info);
            //id_album_binding.setText(""+it.id)
            id_album.apply {
                text= it.id.toString()
                setBackgroundColor(Color.TRANSPARENT)
            }
            id_user.text=""+it.userId
        })
}
}