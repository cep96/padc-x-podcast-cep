package com.cep.cep_podcast.mvp.views

import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.data.vos.PodcastDetailsVO
import com.cep.shared.views.BaseView

interface HomeView: BaseView {

    fun displayPodcast(podcasts: List<DataVO>)

    fun displayRandomPodcast(podcast: PodcastDetailsVO)

    fun showErrorMessage(message: String)
}