package com.cep.cep_podcast.network.responses

import com.cep.cep_podcast.data.vos.ItemVO

data class GetPlayListsInfoAndItemsResponse(
    val description: String,
    val id: String,
    val image: String,
    val items: List<ItemVO>,
    val last_timestamp_ms: Long,
    val listennotes_url: String,
    val name: String,
    val thumbnail: String,
    val total: Int,
    val type: String,
    val visibility: String
)