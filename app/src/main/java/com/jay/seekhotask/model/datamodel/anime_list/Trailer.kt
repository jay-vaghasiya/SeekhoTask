package com.jay.seekhotask.model.datamodel.anime_list

data class Trailer(
    val embed_url: String,
    val images: com.jay.seekhotask.model.datamodel.anime_list.ImagesX,
    val url: String,
    val youtube_id: String
)