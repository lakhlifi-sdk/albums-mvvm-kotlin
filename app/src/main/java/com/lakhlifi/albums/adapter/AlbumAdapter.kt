package com.lakhlifi.albums.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lakhlifi.albums.R
import com.lakhlifi.albums.model.Album
import com.lakhlifi.albums.ui.AlbumInfo
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.*


class AlbumAdapter( val context: Context) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>(), Filterable{
    private var album_list: MutableList<Album> = mutableListOf()

    var albumFilterList = ArrayList<Album>()


    fun setAlbumList(list: List<Album>){

        this.albumFilterList = list as ArrayList<Album>
        this.album_list.addAll(albumFilterList)
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

    class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val album_title = itemView.findViewById<TextView>(R.id.txt_title)
        val album_image =itemView.findViewById<ImageView>(R.id.album_image)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val album: Album = albumFilterList.get(position)
        // Set item views based on your views and data model
        val album_title = viewHolder.album_title
        album_title.text = album.title +" "+ album.id
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
    override fun getItemCount(): Int {
        return albumFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    albumFilterList =  album_list as ArrayList<Album>
                } else {
                    val resultList = ArrayList<Album>()
                    for (row in album_list) {
                        if (row.title.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    albumFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = albumFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                albumFilterList = results?.values as ArrayList<Album>
                Log.d("****","--->"+albumFilterList)
                notifyDataSetChanged()
            }
        }
    }







    fun removeItem(position: Int):Album {
        val item= album_list.removeAt(position)
        notifyItemRemoved(position)
        return item
    }


    fun restoreItem( position: Int,item: Album) {
        album_list.add(position,item)
        notifyItemInserted(position)
    }


    fun addItem( position: Int,item: Album) {
        album_list.add(position,item)
        notifyItemInserted(position)
    }


}