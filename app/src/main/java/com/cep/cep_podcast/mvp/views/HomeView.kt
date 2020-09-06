package com.cep.cep_podcast.mvp.views

import com.cep.cep_podcast.data.vos.ItemVO
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse

interface HomeView: BaseView {

    fun displayPodcast(podcasts: List<ItemVO>)

    fun displayRandomPodcast(podcast: GetRandomEpisodeResponse)
}