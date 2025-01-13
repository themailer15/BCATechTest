package com.example.ifanobcamobiletechtest

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var songRecyclerView: RecyclerView
    private lateinit var btnPlayPause: ImageButton
    private lateinit var btnNext: ImageButton
    private lateinit var btnPrevious: ImageButton

    // Menggunakan ViewModel dengan delegate property 'viewModels'
    private val musicViewModel: MusicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songRecyclerView = findViewById(R.id.songRecyclerView)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnNext = findViewById(R.id.btnNext)
        btnPrevious = findViewById(R.id.btnPrevious)

        // Mengatur RecyclerView
        val songAdapter = SongAdapter(musicViewModel.songs.value ?: emptyList())
        songRecyclerView.layoutManager = LinearLayoutManager(this)
        songRecyclerView.adapter = songAdapter

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
