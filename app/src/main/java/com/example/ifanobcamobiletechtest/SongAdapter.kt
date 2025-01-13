package com.example.ifanobcamobiletechtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private var songs: List<Song>, private val onSongSelected: (Song) -> Unit) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var onSearchSong = songs

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    fun updateSongs(filteredSongs: List<Song>) {
        songs = filteredSongs
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            onSearchSong
        } else {
            onSearchSong.filter { song ->
                song.title.contains(query, ignoreCase = true) || song.artist.contains(query, ignoreCase = true)
            }
        }
        updateSongs(filteredList)
    }

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(song: Song) {
            itemView.findViewById<TextView>(R.id.songTitle).text = song.title
            itemView.findViewById<TextView>(R.id.songArtist).text = song.artist
            itemView.setOnClickListener {
                onSongSelected(song)
            }
        }
    }
}