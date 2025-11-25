package com.example.musicplayer.model

data class PlayerState(
    val currentSong: Song? = null,
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val playlist: List<Song> = emptyList(),
    val currentIndex: Int = -1
) {

    fun hasNext(): Boolean = currentIndex < playlist.size - 1

    fun hasPrevious(): Boolean = currentIndex > 0

    fun getNextSong(): Song? = if (hasNext()) playlist[currentIndex + 1] else null

    fun getPreviousSong(): Song? = if (hasPrevious()) playlist[currentIndex - 1] else null
}