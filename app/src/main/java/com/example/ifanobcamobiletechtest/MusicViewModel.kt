package com.example.ifanobcamobiletechtest

import android.app.Application
import android.media.MediaPlayer
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MusicViewModel(application: Application) : AndroidViewModel(application) {

    private val songRepo = SongRepo()

    // State data
    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> get() = _songs

    private val _currentSong = MutableLiveData<Song?>()
    val currentSong: LiveData<Song?> get() = _currentSong

    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> get() = _isPlaying

    lateinit var mediaPlayer: MediaPlayer
    private var currentIndex = 0

    init {
        _songs.value = songRepo.getSongs()
        _currentSong.value = _songs.value?.getOrNull(currentIndex)
        _isPlaying.value = false
    }

    fun playOrPause() {
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            _isPlaying.value = false
        } else {
            playSong()
        }
    }

    fun nextSong() {
        if (currentIndex < (_songs.value?.size ?: 0) - 1) {
            currentIndex++
            playSong()
        }
    }

    fun previousSong() {
        if (currentIndex > 0) {
            currentIndex--
            playSong()
        }
    }

    private fun playSong() {
        _currentSong.value = _songs.value?.getOrNull(currentIndex)

        // Reset and initialize MediaPlayer
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.reset()
        }

        mediaPlayer = MediaPlayer.create(getApplication(), _currentSong.value?.fileResId ?: return)
        mediaPlayer.start()
        _isPlaying.value = true
    }

    override fun onCleared() {
        super.onCleared()
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}
