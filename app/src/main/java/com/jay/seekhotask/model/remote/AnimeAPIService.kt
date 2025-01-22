package com.jay.seekhotask.model.remote

import com.jay.seekhotask.model.datamodel.anime_detail.AnimeData
import com.jay.seekhotask.model.datamodel.anime_list.AnimeBulk
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeAPIService {
    @GET("/v4/top/anime")
    suspend fun getAnimeData(): Response<AnimeBulk>

    @GET("/v4/anime/{anime_id}")
    suspend fun getAnimeDetails(
        @Path("anime_id") animeId: String
    ): Response<AnimeData>

}