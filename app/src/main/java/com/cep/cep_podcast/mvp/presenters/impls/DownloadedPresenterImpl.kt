package com.cep.cep_podcast.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.cep.cep_podcast.data.dummydata.DummyPodcast
import com.cep.cep_podcast.data.models.PodcastModel
import com.cep.cep_podcast.data.models.PodcastModelImpl
import com.cep.cep_podcast.mvp.presenters.AbstractBasePresenter
import com.cep.cep_podcast.mvp.presenters.DownloadedPresenter
import com.cep.cep_podcast.mvp.views.DownloadView

class DownloadedPresenterImpl: DownloadedPresenter, AbstractBasePresenter<DownloadView>() {

    val mPodcastModel: PodcastModel = PodcastModelImpl

//    val dummyList: ArrayList<DummyPodcast> = arrayListOf(
//        DummyPodcast(1), DummyPodcast(2),
//        DummyPodcast(3), DummyPodcast(5),
//        DummyPodcast(5), DummyPodcast(6),
//        DummyPodcast(7), DummyPodcast(8),
//        DummyPodcast(9), DummyPodcast(10)
//    )

    override fun onUIReadyForDownloadedPodcast(lifecycleOwner: LifecycleOwner) {
//        mView?.displayDownloadedPodcast(dummyList)
        mPodcastModel.getAudioDownloaded()
            .observe(lifecycleOwner, Observer {
                mView?.displayDownloadedPodcast(it)
            })

    }
}