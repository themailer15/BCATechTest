package com.example.ifanobcamobiletechtest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var songRecyclerView: RecyclerView
    private lateinit var btnPlayPause: ImageButton
    private lateinit var btnNext: ImageButton
    private lateinit var btnPrevious: ImageButton
    private lateinit var currentSongBar: ConstraintLayout

    private val musicViewModel: MusicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songRecyclerView = findViewById(R.id.songRecyclerView)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnNext = findViewById(R.id.btnNext)
        btnPrevious = findViewById(R.id.btnPrevious)
        currentSongBar = findViewById(R.id.currentSongBar)

        // Mengatur RecyclerView
        val songAdapter = SongAdapter(musicViewModel.songs.value ?: emptyList()) { selectedSong ->
            musicViewModel.selectSong(selectedSong)  // Pass the selected song to the ViewModel
        }
        songRecyclerView.layoutManager = LinearLayoutManager(this)
        songRecyclerView.adapter = songAdapter

        musicViewModel.isPlaying.observe(this, Observer { isPlaying ->
            btnPlayPause.setImageResource(
                if (isPlaying == true) android.R.drawable.ic_media_pause
                else android.R.drawable.ic_media_play
            )
        })

        musicViewModel.isSongPlayed.observe(this, Observer { isSongPlayed ->
            currentSongBar.visibility = if (isSongPlayed == true) View.VISIBLE else View.GONE
        })

        btnPlayPause.setOnClickListener {
            musicViewModel.playOrPause()
        }

        btnNext.setOnClickListener {
            musicViewModel.nextSong()
        }

        btnPrevious.setOnClickListener {
            musicViewModel.previousSong()
        }
    }
}