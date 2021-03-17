package com.lakhlifi.albums.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lakhlifi.albums.R
import com.lakhlifi.albums.network.model.Album
import com.lakhlifi.albums.view.AlbumInfo
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


class AlbumAdapter( val context: Context) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
     private var album_list: MutableList<Album> = mutableListOf()
    fun setAlbumList(list: List<Album>){
        this.album_list = list.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int):Album {
       val item= album_list.removeAt(position)
        notifyItemRemoved(position)
        return item
    }
    fun getNotAt(position: Int):Album{
        return album_list.get(position)
    }

    fun restoreItem( position: Int,item: Album) {
        album_list.add(position,item)
        notifyItemInserted(position)
    }
    fun addItem( position: Int,item: Album) {
        album_list.add(position,item)
        notifyItemInserted(position)
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
        val album_image =itemView.findViewById<ImageView>(R.id.album_image)
    }
    fun getAlbumAt(position: Int): Album {
        return album_list.get(position)
    }
    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: AlbumAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val album: Album = album_list.get(position)
        // Set item views based on your views and data model
        val album_title = viewHolder.album_title
        album_title.setText(album.title)
        Picasso.get()
            .load("https://picsum.photos/200/300")
            .placeholder(R.drawable.image)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .into(viewHolder.album_image)
        viewHolder.itemView.setOnClickListener {
            val i = Intent(context, AlbumInfo::class.java)
            i.putExtra("id", album.id)
            //animation for image
            val p1=Pair.create<View, String>(viewHolder.album_image, "transition_image")
            //animation for title
            val p2=Pair.create<View, String>(viewHolder.album_image, "transition_title")
            val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity,  p1,  p2)
            context.startActivity(i,options.toBundle())
        }
    }
    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return album_list.size
    }
}