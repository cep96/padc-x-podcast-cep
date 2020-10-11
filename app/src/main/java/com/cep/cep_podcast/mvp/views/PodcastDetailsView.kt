package com.cep.cep_podcast.mvp.views

import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.cep_podcast.data.vos.PodcastDetailsVO
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.cep.shared.views.BaseView

interface PodcastDetailsView: BaseView {

    fun displayPodcastDetails(podcast: DataVO)
    fun displayDownloadedPodcastDetails(podcast: DownloadedPodcastVO)

    fun showErrorMessage(message: String)
}