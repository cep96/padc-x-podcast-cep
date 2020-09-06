package com.cep.cep_podcast.mvp.presenters.impls

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.cep.cep_podcast.data.models.PodcastModel
import com.cep.cep_podcast.data.models.PodcastModelImpl
import com.cep.cep_podcast.device.player.MediaPlayerImpl
import com.cep.cep_podcast.mvp.presenters.AbstractBasePresenter
import com.cep.cep_podcast.mvp.presenters.PodcastDetailsPresenter
import com.cep.cep_podcast.mvp.views.PodcastDetailsView
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import io.reactivex.android.schedulers.AndroidSchedulers

class PodcastDetailsPresenterImpl: PodcastDetailsPresenter,
    AbstractBasePresenter<PodcastDetailsView>() {

    private val mediaPlayer = MediaPlayerImpl()

    val mPodcastModel: PodcastModel = PodcastModelImpl

    @SuppressLint("CheckResult")
    override fun onUIReadyForPodcast(lifecycleOwner: LifecycleOwner, podcastId: String, podcasType: String) {
        if (podcasType == "download"){
            mPodcastModel.getAudioDownloadedData(podcastId)
                .observe(lifecycleOwner, Observer {
                    mView?.displayDownloadedPodcastDetails(it!!)
                })
        }else{
            val podcast = MutableLiveData<GetDetailsEpisodeResponse>()
            mPodcastModel.getDetailsPodcast(podcastId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Podcast Details", "==> $it")
                    podcast.postValue(it)
                },{
                    podcast.postValue(null)
                })

            podcast.observe(lifecycleOwner, Observer {
                it.apply {
                    mView?.displayPodcastDetails(it!!)
                }
            })
        }
    }

    override fun deactivate() {
    }

    override fun getPlayer() = mediaPlayer

    override fun play(url: String) = mediaPlayer.play(url)

    override fun releasePlayer() = mediaPlayer.releasePlayer()

}