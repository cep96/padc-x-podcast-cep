package com.cep.cep_podcast.mvp.views

import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.shared.views.BaseView

interface DownloadView: BaseView {

    fun displayDownloadedPodcast(podcast: List<DownloadedPodcastVO>)
}