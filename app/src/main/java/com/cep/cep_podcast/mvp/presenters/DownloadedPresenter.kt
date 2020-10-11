package com.cep.cep_podcast.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.cep.cep_podcast.mvp.views.DownloadView
import com.cep.shared.presenters.BasePresenter

interface DownloadedPresenter: BasePresenter<DownloadView> {

    fun onUIReadyForDownloadedPodcast(lifecycleOwner: LifecycleOwner)
}