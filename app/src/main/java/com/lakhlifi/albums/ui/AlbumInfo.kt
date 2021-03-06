package com.lakhlifi.albums.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lakhlifi.albums.R
import com.lakhlifi.albums.databinding.ActivityAlbumInfoBinding
import com.lakhlifi.albums.viewModel.AlbumInfoViewModel
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


class AlbumInfo : AppCompatActivity() {
    lateinit var binding:ActivityAlbumInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding= DataBindingUtil.setContentView(this,R.layout.activity_album_info)

        val album_title_binding=binding.albumTitle
        val album_image_binding=binding.imageInfo

        val id_album=findViewById<TextView>(R.id.id_Album)
        val id_user=findViewById<TextView>(R.id.id_user)


        val extras = intent.extras ?: return
        val id = extras.getInt("id")
        var albumInfoViewModel: AlbumInfoViewModel = ViewModelProvider(this).get(AlbumInfoViewModel::class.java)
        //filter using the method getAlbum
        albumInfoViewModel.getAlbum(application,id)
        albumInfoViewModel.album.observe(this, Observer{
            album_title_binding.setText(it.title)
           Picasso
               .get()
               .load("https://picsum.photos/id/${it.id}/200")
               .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
               .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
               .placeholder(R.drawable.image)
               .into(album_image_binding);
            id_album.apply {
                text= it.id.toString()
                setBackgroundColor(Color.TRANSPARENT)
            }
            id_user.text=""+it.userId
        })
}
}