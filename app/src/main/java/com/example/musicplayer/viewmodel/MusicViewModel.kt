package com.example.musicplayer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayer.model.PlayerState
import com.example.musicplayer.model.Song
import com.example.musicplayer.repository.MusicRepository
import com.example.musicplayer.service.MusicServiceController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MusicViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MusicRepository(application)
    private val serviceController = MusicServiceController(application)

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _currentSongIndex = MutableStateFlow(-1)
    val currentSongIndex: StateFlow<Int> = _currentSongIndex.asStateFlow()

    val playerState: StateFlow<PlayerState> = combine(
        _songs,
        serviceController.isPlaying,
        serviceController.currentPosition,
        serviceController.duration,
        _currentSongIndex
    ) { songs, isPlaying, position, duration, index ->
        PlayerState(
            currentSong = if (index >= 0 && index < songs.size) songs[index] else null,
            isPlaying = isPlaying,
            currentPosition = position,
            duration = duration,
            playlist = songs,
            currentIndex = index
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PlayerState()
    )

    init {
        loadSongs()
    }

    fun loadSongs() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val songsList = repository.getAllSongs()
                _songs.value = songsList
            } catch (e: Exception) {
                e.printStackTrace()
                _songs.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun playSong(song: Song) {
        val index = _songs.value.indexOf(song)
        if (index != -1) {
            _currentSongIndex.value = index
            if (_songs.value.isNotEmpty()) {
                serviceController.setPlaylist(_songs.value, index)
            }
        }
    }

    fun togglePlayPause() {
        serviceController.togglePlayPause()
    }

    fun skipToNext() {
        val nextIndex = _currentSongIndex.value + 1
        if (nextIndex < _songs.value.size) {
            _currentSongIndex.value = nextIndex
            serviceController.skipToNext()
        }
    }

    fun skipToPrevious() {
        val prevIndex = _currentSongIndex.value - 1
        if (prevIndex >= 0) {
            _currentSongIndex.value = prevIndex
            serviceController.skipToPrevious()
        }
    }

    fun seekTo(position: Long) {
        serviceController.seekTo(position)
    }

    override fun onCleared() {
        super.onCleared()
        serviceController.release()
    }
}