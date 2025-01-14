package com.example.ifanobcamobiletechtest

class SongRepo {
    private val songs = listOf(
        Song(1, R.drawable.asal_kau_bahagia_armada_pic,"Asal Kau Bahagia", "Armada", "Album 1", R.raw.armada_asal_kau_bahagia),
        Song(2, R.drawable.blue_yung_kai_pic,"Blue", "Yung Kai", "Album 2", R.raw.yung_kai_blue),
        Song(3, R.drawable.alesso_heroes_pic,"Heroes", "Alesso", "Album 3", R.raw.alesso_heroes),
        Song(4, R.drawable.lonely_together_avicii_pic,"Lonely Together", "Avicii", "Album 4", R.raw.avicii_lonely_together),
        Song(5, R.drawable.adam_levine_lost_star_pic, "Lost Stars", "Adam Levine", "Album 5", R.raw.adam_levine_lost_stars),
        Song(6, R.drawable.meant_to_be_bebe_rexha_pic,"Meant To Be", "Bebe Rexha", "Album 6", R.raw.bebe_rexha_meant_to_be)
    )

    fun getSongs(): List<Song> = songs
}