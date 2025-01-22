package com.jay.seekhotask.model.repository

import com.jay.seekhotask.model.remote.AnimeApiDataServiceInstance
import java.io.IOException

class AnimeListRepositoryImpl() : AnimeListRepository {

    override suspend fun getAnimeListData(): List<com.jay.seekhotask.model.datamodel.anime_list.Data> {
        return try {
            val response = AnimeApiDataServiceInstance.api.getAnimeData()
            if (response.isSuccessful) {
                response.body()?.data ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}

