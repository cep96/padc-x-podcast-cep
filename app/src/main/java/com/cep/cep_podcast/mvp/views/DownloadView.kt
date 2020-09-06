package com.cep.cep_podcast.mvp.views

import com.cep.cep_podcast.data.vos.DownloadedPodcastVO

interface DownloadView: BaseView {

    fun displayDownloadedPodcast(podcast: List<DownloadedPodcastVO>)
}