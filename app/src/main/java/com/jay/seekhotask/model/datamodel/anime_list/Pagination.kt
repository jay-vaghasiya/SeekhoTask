package com.jay.seekhotask.model.datamodel.anime_list

data class Pagination(
    val current_page: Int,
    val has_next_page: Boolean,
    val items: com.jay.seekhotask.model.datamodel.anime_list.Items,
    val last_visible_page: Int
)