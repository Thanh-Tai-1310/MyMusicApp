package com.example.musicplayer.model

import android.net.Uri

/**
 * Data class representing a song/audio file
 */
data class Song(
    val id: Long,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long, // in milliseconds
    val uri: Uri,
    val albumArtUri: Uri? = null
) {
    /**
     * Format duration from milliseconds to MM:SS format
     */
    fun getFormattedDuration(): String {
        val totalSeconds = duration / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}