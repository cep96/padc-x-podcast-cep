package com.cep.cep_podcast.network.apis

import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.data.vos.GenreVO
import com.cep.cep_podcast.data.vos.PodcastDetailsVO
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse

interface FirebaseApi {

    fun getPodcasts(onSuccess: (List<DataVO>) -> Unit, onFailure: (String) -> Unit)
    fun getRandom(onSuccess: (List<PodcastDetailsVO>) -> Unit, onFailure: (String) -> Unit)
//    fun getDetails(id: String, onSuccess: (PodcastDetailsVO) -> Unit, onFailure: (String) -> Unit)
    fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit)
}