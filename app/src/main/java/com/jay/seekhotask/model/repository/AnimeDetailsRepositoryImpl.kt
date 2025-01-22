package com.jay.seekhotask.model.repository

import com.jay.seekhotask.model.datamodel.anime_detail.AnimeData
import com.jay.seekhotask.model.remote.AnimeApiDataServiceInstance
import java.io.IOException

class AnimeDetailsRepositoryImpl() : AnimeDetailsRepository {

    override suspend fun getAnimeData(id:String): AnimeData? {
        return try {
            val response = AnimeApiDataServiceInstance.api.getAnimeDetails(id)

            if (response.isSuccessful) {
                response.body()
            } else {
                throw Exception(response.message())
            }
        } catch (e: IOException) {
            throw Exception(e.message)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}
