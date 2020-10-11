package com.cep.cep_podcast.mvp.presenters.impls

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.cep.cep_podcast.data.models.PodcastModel
import com.cep.cep_podcast.data.models.PodcastModelImpl
import com.cep.cep_podcast.device.player.MediaPlayerImpl
import com.cep.cep_podcast.mvp.presenters.HomePresenter
import com.cep.cep_podcast.mvp.views.HomeView
import com.cep.shared.presenters.AbstractBasePresenter
import java.io.File

class HomePresenterImpl: HomePresenter, AbstractBasePresenter<HomeView>() {

    val mPodcastModel: PodcastModel = PodcastModelImpl
    private val mediaPlayer = MediaPlayerImpl()

    override fun onUIReadyForPodcasts(lifecycleOwner: LifecycleOwner) {
//        loadUpNextPodcastFromApi()
//        requestUpNextPodcast(lifecycleOwner)
        mPodcastModel.getUpNextPodcastFromFirebaseAndSaveToDB(onFailure = {
        })
        mPodcastModel.getUpNextPodcasts()
            .observe(lifecycleOwner, Observer {
                it.apply {
                    mView?.displayPodcast(it)
                }

            })
    }

    @SuppressLint("CheckResult")
    override fun onUIReadyForRandomPodcast(lifecycleOwner: LifecycleOwner) {
        /*
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
         */
        mPodcastModel.getRandomPodcastFromFirebaseAndSaveToDB(onError = {

        })
        mPodcastModel.getRandomPodcast()
            .observe(lifecycleOwner, Observer {
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
//                mView?.displayPodcast(it)
            })
    }

    private fun loadUpNextPodcastFromApi() {
//        mPodcastModel.getUpNextPodcastsFromApiAndSaveToDB (
//            onError = {}
//        )
    }

}