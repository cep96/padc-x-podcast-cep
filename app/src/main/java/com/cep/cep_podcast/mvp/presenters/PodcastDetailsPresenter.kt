package com.cep.cep_podcast.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.cep.cep_podcast.device.player.MediaPlayer
import com.cep.cep_podcast.mvp.views.PodcastDetailsView
import com.cep.shared.presenters.BasePresenter

interface PodcastDetailsPresenter: BasePresenter<PodcastDetailsView> {

    fun onUIReadyForPodcast(lifecycleOwner: LifecycleOwner, podcastId: String, podcastType: String)

    fun deactivate()

    fun getPlayer(): MediaPlayer

    fun play(url: String)

    fun releasePlayer()
}