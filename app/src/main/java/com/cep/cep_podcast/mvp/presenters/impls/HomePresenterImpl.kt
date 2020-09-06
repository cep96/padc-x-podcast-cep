package com.cep.cep_podcast.mvp.presenters.impls

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.cep.cep_podcast.data.models.PodcastModel
import com.cep.cep_podcast.data.models.PodcastModelImpl
import com.cep.cep_podcast.device.player.MediaPlayerImpl
import com.cep.cep_podcast.mvp.presenters.AbstractBasePresenter
import com.cep.cep_podcast.mvp.presenters.HomePresenter
import com.cep.cep_podcast.mvp.views.HomeView
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.File

class HomePresenterImpl: HomePresenter, AbstractBasePresenter<HomeView>() {

    val mPodcastModel: PodcastModel = PodcastModelImpl
    private val mediaPlayer = MediaPlayerImpl()

    override fun onUIReadyForPodcasts(lifecycleOwner: LifecycleOwner) {
        loadUpNextPodcastFromApi()
        requestUpNextPodcast(lifecycleOwner)
    }

    @SuppressLint("CheckResult")
    override fun onUIReadyForRandomPodcast(lifecycleOwner: LifecycleOwner) {
        val podcast = MutableLiveData<GetRandomEpisodeResponse>()
        mPodcastModel.getRandomPodcast()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                podcast.postValue(it)
            },{
                podcast.postValue(null)
            })

        podcast.observe(lifecycleOwner, Observer {
            it.apply {
                mView?.displayRandomPodcast(it)
            }
        })
    }

    override fun onReadyForDownload(
        lifecycleOwner: LifecycleOwner, file: File, audioId: String) {
            mPodcastModel.getDownloadedPodcastFileAndSaveToDB(
                onError = {},
                audioId = audioId,
                audioUrl = Uri.fromFile(file).toString()
            )
    }

    override fun deactivate() {
    }

    override fun getPlayer() = mediaPlayer

    override fun play(url: String) = mediaPlayer.play(url)

    override fun releasePlayer() = mediaPlayer.releasePlayer()

    private fun requestUpNextPodcast(lifecycleOwner: LifecycleOwner) {
        mPodcastModel.getUpNextPodcasts()
            .observe(lifecycleOwner, Observer {
                Log.d("UpNext count", " ==> ${it.count()}")
                mView?.displayPodcast(it)
            })
    }

    private fun loadUpNextPodcastFromApi() {
        mPodcastModel.getUpNextPodcastsFromApiAndSaveToDB (
            onError = {}
        )
    }

}