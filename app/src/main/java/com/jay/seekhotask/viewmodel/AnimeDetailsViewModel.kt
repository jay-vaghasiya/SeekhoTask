package com.jay.seekhotask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.seekhotask.model.datamodel.anime_detail.AnimeData
import com.jay.seekhotask.model.repository.AnimeDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(private val animeListRepository: AnimeDetailsRepository) : ViewModel() {

    private val _animeData =
        MutableStateFlow<AnimeData?>(null)
    val animeData: StateFlow<AnimeData?> = _animeData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchAnimeData(id: String) {
        viewModelScope.launch {
            val data = animeListRepository.getAnimeData(id)
            if (data == null) {
                _errorMessage.value = "Failed to load anime data. Please try again later."
            } else {
                _animeData.value = data
            }
        }
    }
}