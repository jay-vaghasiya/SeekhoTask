package com.jay.seekhotask.model.remote

object AnimeApiDataServiceInstance {
    val api: AnimeAPIService by lazy {
        NetworkModule.provideRetrofit()
            .create(AnimeAPIService::class.java)
    }
}