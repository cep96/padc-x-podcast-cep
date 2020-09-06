package com.cep.cep_podcast.mvp.views

import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse

interface PodcastDetailsView: BaseView {

    fun displayPodcastDetails(podcast: GetDetailsEpisodeResponse)
    fun displayDownloadedPodcastDetails(podcast: DownloadedPodcastVO)
}