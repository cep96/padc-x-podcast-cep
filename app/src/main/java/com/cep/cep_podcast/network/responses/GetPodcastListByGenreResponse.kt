package com.cep.cep_podcast.network.responses

import com.cep.cep_podcast.data.vos.PodcastVO

data class GetPodcastListByGenreResponse(
    val has_next: Boolean,
    val has_previous: Boolean,
    val id: Int,
    val listennotes_url: String,
    val name: String,
    val next_page_number: Int,
    val page_number: Int,
    val parent_id: Int,
    val podcasts: List<PodcastVO>,
    val previous_page_number: Int,
    val total: Int
)