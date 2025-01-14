package com.example.ifanobcamobiletechtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private var songs: List<Song>, private val onSongSelected: (Song) -> Unit, currentIndexLiveData: LiveData<Int>, musicViewModel: MusicViewModel) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private val musicViewModel = musicViewModel
    private var onSearchSong = songs
    private var selectedIndex: Int = -1

    init {
        currentIndexLiveData.observeForever { index ->
            selectedIndex = index
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        val isSelected = position == selectedIndex
        val isPlaying = position == selectedIndex && musicViewModel.isPlaying.value == true

        holder.bind(song, isSelected, isPlaying)

        Log.d("Position", position.toString())
        Log.d("Position2", selectedIndex.toString())
    }

    override fun getItemCount(): Int = songs.size

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
        fun bind(song: Song, isSelected: Boolean, isPlaying: Boolean) {
            itemView.findViewById<ImageView>(R.id.songImage).setImageResource(song.albumImg)
            itemView.findViewById<TextView>(R.id.songTitle).text = song.title
            itemView.findViewById<TextView>(R.id.songArtist).text = song.artist

            val soundIndicator = itemView.findViewById<ImageView>(R.id.soundIndicator)

            if (isSelected) {
                itemView.setBackgroundColor(itemView.context.getColor(R.color.selected_item)) // Highlight item
                soundIndicator.visibility = View.VISIBLE
                soundIndicator.setImageResource(
                    if (isPlaying) R.drawable.sound_wave
                    else R.drawable.pause
                )
            } else {
                itemView.setBackgroundColor(itemView.context.getColor(android.R.color.transparent))
                soundIndicator.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onSongSelected(song)
            }
        }
    }
}
