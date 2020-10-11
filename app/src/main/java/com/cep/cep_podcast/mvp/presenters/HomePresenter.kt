package com.cep.cep_podcast.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.cep.cep_podcast.device.player.MediaPlayer
import com.cep.cep_podcast.device.player.MediaPlayerImpl
import com.cep.cep_podcast.mvp.views.HomeView
import com.cep.shared.presenters.BasePresenter
import java.io.File

interface HomePresenter: BasePresenter<HomeView> {

    fun onUIReadyForPodcasts(lifecycleOwner: LifecycleOwner)

    fun onUIReadyForRandomPodcast(lifecycleOwner: LifecycleOwner)

    fun onReadyForDownload(lifecycleOwner: LifecycleOwner, file: File, audioId: String)

    fun deactivate()

    fun getPlayer(): MediaPlayer

    fun play(url: String)

    fun releasePlayer()

//    fun setMediaSessionState(isActive: Boolean)
}