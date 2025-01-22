package com.jay.seekhotask.model.repository

interface AnimeListRepository {
    suspend fun getAnimeListData(): List<com.jay.seekhotask.model.datamodel.anime_list.Data>
}
