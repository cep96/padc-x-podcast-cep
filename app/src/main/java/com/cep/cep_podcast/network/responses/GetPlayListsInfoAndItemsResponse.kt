package com.cep.cep_podcast.network.responses

import com.cep.cep_podcast.data.vos.ItemVO
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class GetPlayListsInfoAndItemsResponse(
    var description: String? = "",
    var id: String? = "",
    var image: String? = "",
    var items: List<ItemVO>? = arrayListOf(),
    var last_timestamp_ms: Long? = 0,
    var listennotes_url: String? = "",
    var name: String? = "",
    var thumbnail: String? = "",
    var total: Int? = 0,
    var type: String? = "",
    var visibility: String? = ""
)