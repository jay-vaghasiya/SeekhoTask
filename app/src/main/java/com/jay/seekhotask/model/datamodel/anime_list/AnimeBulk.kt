package com.jay.seekhotask.model.datamodel.anime_list

data class AnimeBulk(
    val `data`: List<com.jay.seekhotask.model.datamodel.anime_list.Data>,
    val pagination: com.jay.seekhotask.model.datamodel.anime_list.Pagination
)