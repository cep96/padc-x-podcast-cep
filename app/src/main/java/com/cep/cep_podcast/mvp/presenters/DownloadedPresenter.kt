package com.cep.cep_podcast.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.cep.cep_podcast.mvp.views.DownloadView

interface DownloadedPresenter: BasePresenter<DownloadView> {

    fun onUIReadyForDownloadedPodcast(lifecycleOwner: LifecycleOwner)
}