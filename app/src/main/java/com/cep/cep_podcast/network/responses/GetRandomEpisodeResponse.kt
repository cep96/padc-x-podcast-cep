package com.cep.cep_podcast.network.responses

import com.cep.cep_podcast.data.vos.PodcastVO
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class GetRandomEpisodeResponse(
    var audio: String? = "",
    var audio_length_sec: Int? = 0,
    var description: String? = "",
    var explicit_content: Boolean? = false,
    var id: String? = "",
    var image: String? = "",
    var link: String? = "",
    var listennotes_edit_url: String? = "",
    var listennotes_url: String? = "",
    var maybe_audio_invalid: Boolean? = false,
    var podcast: PodcastVO? = null,
    val pub_date_ms: Long? = 0,
    val thumbnail: String? = "",
    var title: String? = ""
)