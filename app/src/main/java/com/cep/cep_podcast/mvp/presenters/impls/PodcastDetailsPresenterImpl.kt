package com.cep.cep_podcast.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.cep.cep_podcast.data.models.PodcastModel
import com.cep.cep_podcast.data.models.PodcastModelImpl
import com.cep.cep_podcast.device.player.MediaPlayerImpl
import com.cep.cep_podcast.mvp.presenters.PodcastDetailsPresenter
import com.cep.cep_podcast.mvp.views.PodcastDetailsView
import com.cep.shared.presenters.AbstractBasePresenter

class PodcastDetailsPresenterImpl: PodcastDetailsPresenter,
    AbstractBasePresenter<PodcastDetailsView>() {

    private val mediaPlayer = MediaPlayerImpl()

    val mPodcastModel: PodcastModel = PodcastModelImpl

    override fun onUIReadyForPodcast(
        lifecycleOwner: LifecycleOwner,
        podcastId: String,
        podcastType: String
    ) {
        mPodcastModel.getPodcastDetails(podcastId)
            .observe(lifecycleOwner, Observer {
                mView?.displayPodcastDetails(it)
            })

    }

//    @SuppressLint("CheckResult")
//    override fun onUIReadyForPodcast(lifecycleOwner: LifecycleOwner, podcastId: String, podcasType: String) {
//        if (podcasType == "download"){
//            mPodcastModel.getAudioDownloadedData(podcastId)
//                .observe(lifecycleOwner, Observer {
//                    mView?.displayDownloadedPodcastDetails(it!!)
//                })
//        }else{
//            val podcast = MutableLiveData<GetDetailsEpisodeResponse>()
//            mPodcastModel.getDetailsPodcast(podcastId)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    Log.d("Podcast Details", "==> $it")
//                    podcast.postValue(it)
//                },{
//                    podcast.postValue(null)
//                })
//
//            podcast.observe(lifecycleOwner, Observer {
//                it.apply {
//                    mView?.displayPodcastDetails(it!!)
//                }
//            })
//        }
//    }

    override fun deactivate() {
    }

    override fun getPlayer() = mediaPlayer

    override fun play(url: String) = mediaPlayer.play(url)

    override fun releasePlayer() = mediaPlayer.releasePlayer()

}