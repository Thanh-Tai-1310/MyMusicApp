package com.example.musicplayer.model

/**
 * Represents the current state of the music player
 */
data class PlayerState(
    val currentSong: Song? = null,
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val playlist: List<Song> = emptyList(),
    val currentIndex: Int = -1
) {
    /**
     * Check if there's a next song available
     */
    fun hasNext(): Boolean = currentIndex < playlist.size - 1

    /**
     * Check if there's a previous song available
     */
    fun hasPrevious(): Boolean = currentIndex > 0

    /**
     * Get the next song if available
     */
    fun getNextSong(): Song? = if (hasNext()) playlist[currentIndex + 1] else null

    /**
     * Get the previous song if available
     */
    fun getPreviousSong(): Song? = if (hasPrevious()) playlist[currentIndex - 1] else null
}