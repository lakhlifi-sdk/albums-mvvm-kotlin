package com.lakhlifi.albums.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lakhlifi.albums.R
import com.lakhlifi.albums.network.model.Album
import com.squareup.picasso.Picasso

class AlbumAdapter( val context: Context) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
     private var album_list: List<Album> = ArrayList()


    fun setAlbumList(list: List<Album>){
        this.album_list = list
        notifyDataSetChanged()
    }

    //get view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val albumView = inflater.inflate(R.layout.album_item, parent, false)
        // Return a new holder instance
        return ViewHolder(albumView)
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val album_title = itemView.findViewById<TextView>(R.id.txt_title)


    }


    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: AlbumAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val album: Album = album_list.get(position)
        // Set item views based on your views and data model
        val album_title = viewHolder.album_title
        //val textViewEmail=viewHolder.user_email
        //val image_user=viewHolder.user_image

        album_title.setText(album.title)
        //textViewEmail.setText(user.email)

        //Picasso.get().load("https://picsum.photos/seed/picsum/200/300").into(viewHolder.user_image);

        /*viewHolder.itemView.setOnClickListener {
            val i = Intent(context, UserInfo::class.java)
            i.putExtra("code", album.title)
            context.startActivity(i)
        }*/
    }

    fun setAndRefresh(albums: MutableList<Album>) {
        this.album_list = albums
        notifyDataSetChanged()
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return album_list.size
    }
}