package com.example.ifanobcamobiletechtest

class SongRepo {
    private val songs = listOf(
        Song(1, "Lost Stars", "Adam Levine", "Album 1", R.raw.adam_levine_lost_stars),
        Song(2, "Heroes", "Alesso", "Album 2", R.raw.alesso_heroes),
        Song(3, "Asal Kau Bahagia", "Armada", "Album 3", R.raw.armada_asal_kau_bahagia),
        Song(3, "Lonely Together", "Avicii", "Album 4", R.raw.avicii_lonely_together),
        Song(3, "Meant To Be", "Bebe Rexha", "Album 5", R.raw.bebe_rexha_meant_to_be)
    )

    fun getSongs(): List<Song> = songs
}