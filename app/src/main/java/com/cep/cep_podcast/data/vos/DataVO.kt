package com.cep.cep_podcast.data.vos

data class DataVO(
    val audio: String,
    val audio_length_sec: Int,
    val description: String,
    val explicit_content: Boolean,
    val id: String,
    val image: String,
    val link: String,
    val listennotes_edit_url: String,
    val listennotes_url: String,
    val maybe_audio_invalid: Boolean,
    val podcast: PodcastVO,
    val pub_date_ms: Long,
    val thumbnail: String,
    val title: String
)