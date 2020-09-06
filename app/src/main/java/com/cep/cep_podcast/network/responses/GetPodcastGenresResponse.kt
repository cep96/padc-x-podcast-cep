package com.cep.cep_podcast.network.responses

import com.cep.cep_podcast.data.vos.GenreVO

data class GetPodcastGenresResponse(
    val genres: List<GenreVO>
)