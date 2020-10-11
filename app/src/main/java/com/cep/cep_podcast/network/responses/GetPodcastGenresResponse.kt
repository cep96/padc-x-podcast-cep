package com.cep.cep_podcast.network.responses

import com.cep.cep_podcast.data.vos.GenreVO
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class GetPodcastGenresResponse(
    var genres: List<GenreVO>? = arrayListOf()
)