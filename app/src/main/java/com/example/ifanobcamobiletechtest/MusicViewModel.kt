package com.example.ifanobcamobiletechtest

import android.app.Application
import android.media.MediaPlayer
import android.util.Log
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MusicViewModel(application: Application) : AndroidViewModel(application) {

    private val songRepo = SongRepo()

    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> get() = _songs

    private val _currentSong = MutableLiveData<Song?>()
    val currentSong: LiveData<Song?> get() = _currentSong

    private val _isSongPlayed = MutableLiveData<Boolean>()
    val isSongPlayed: LiveData<Boolean> get() = _isSongPlayed

    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> get() = _isPlaying

    private val _currentIndex = MutableLiveData<Int>()
    val currentIndexLiveData: LiveData<Int> get() = _currentIndex

    lateinit var mediaPlayer: MediaPlayer
    var currentIndex = -1


    init {
        _songs.value = songRepo.getSongs()
        _currentSong.value = _songs.value?.getOrNull(currentIndex)
        _isPlaying.value = false
        _isSongPlayed.value = false
        _currentIndex.value = currentIndex // Inisialisasi LiveData
    }

    fun selectSong(song: Song) {
        currentIndex = _songs.value?.indexOf(song) ?: 0
        _currentIndex.value = currentIndex // Update LiveData
        Log.d("Index", currentIndex.toString())
        playSong()
    }

    fun playOrPause() {
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            _isPlaying.value = false
        } else {
            mediaPlayer.start()
            _isPlaying.value = true
        }
    }

    fun nextSong() {
        if (currentIndex < (_songs.value?.size ?: 0) - 1) {
            currentIndex++
            _currentIndex.value = currentIndex
            Log.d("IndexNext", currentIndex.toString())
            Log.d("IndexNext2", _songs.value?.size.toString())
            playSong()
        }
    }

    fun previousSong() {
        if (currentIndex > 0) {
            currentIndex--
            _currentIndex.value = currentIndex // Update LiveData
            Log.d("IndexPrev", currentIndex.toString())
            playSong()
        }
    }

    private fun playSong() {
        _currentSong.value = _songs.value?.getOrNull(currentIndex)


        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.reset()
        }

        mediaPlayer = MediaPlayer.create(getApplication(), _currentSong.value?.fileResId ?: return)
        mediaPlayer.setOnCompletionListener {
            nextSong()
        }

        mediaPlayer.start()

        _isPlaying.value = true
        _isSongPlayed.value = true
    }

    override fun onCleared() {
        super.onCleared()
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}