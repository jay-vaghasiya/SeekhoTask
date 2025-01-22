package com.jay.seekhotask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.seekhotask.model.repository.AnimeListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeViewModel(private val animeListRepository: AnimeListRepository) : ViewModel() {

    private val _animeList = MutableStateFlow<List<com.jay.seekhotask.model.datamodel.anime_list.Data>>(emptyList())
    val animeList: StateFlow<List<com.jay.seekhotask.model.datamodel.anime_list.Data>> = _animeList

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchAnimeList() {
        viewModelScope.launch {
            val data = animeListRepository.getAnimeListData()
            if (data.isEmpty()) {
                _errorMessage.value = "Failed to load anime list. Please try again later."
            } else {
                _animeList.value = data
            }
        }
    }
}
