package com.example.ifanobcamobiletechtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private var songs: List<Song>) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_song, parent, false) // Layout untuk item lagu
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.songTitle.text = song.title
        holder.songArtist.text = song.artist
        holder.songAlbum.text = song.album
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songTitle: TextView = itemView.findViewById(R.id.songTitle)
        val songArtist: TextView = itemView.findViewById(R.id.songArtist)
        val songAlbum: TextView = itemView.findViewById(R.id.songAlbum)
    }
}

