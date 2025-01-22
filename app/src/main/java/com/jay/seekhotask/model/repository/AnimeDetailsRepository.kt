package com.jay.seekhotask.model.repository

import com.jay.seekhotask.model.datamodel.anime_detail.AnimeData

interface AnimeDetailsRepository {
    suspend fun getAnimeData(id: String): AnimeData?
}