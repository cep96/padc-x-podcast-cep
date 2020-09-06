package com.cep.cep_podcast.data.models

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.cep_podcast.data.vos.GenreVO
import com.cep.cep_podcast.data.vos.ItemVO
import com.cep.cep_podcast.data.vos.PodcastVO
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PodcastModelImpl: PodcastModel, BaseModel() {

    @SuppressLint("CheckResult")
    override fun getUpNextPodcastsFromApiAndSaveToDB(onError: (String) -> Unit) {
        providePodcastApiService().getPlayListsAndItems("m1pe7z60bsw")
            .map { it.items?.toList() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Up Next Items", "==> $it")
                mPodcastDB.podcastDao().insertUpNextItems(it!!)
            },{
                onError(it.localizedMessage)
            })
    }

    override fun getUpNextPodcasts(): LiveData<List<ItemVO>> {
        return mPodcastDB.podcastDao()
            .getUpNextItems()
    }

    @SuppressLint("CheckResult")
    override fun getGenresFromApiAndSaveToDB(onError: (String) -> Unit) {
        providePodcastApiService().getPodcastGenres(1)
            .map { it.genres?.toList() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mPodcastDB.podcastDao().insertGenres(it)
            },{
                onError(it.localizedMessage)
            })
    }

    override fun getGenres(): LiveData<List<GenreVO>> {
        return mPodcastDB.podcastDao()
            .getGenres()
    }

    @SuppressLint("CheckResult")
    override fun getPodcastsByGenreFromApiAndSaveToDB(generId: Int, onError: (String) -> Unit) {
        providePodcastApiService().getBestPodcastByGenres(generId, 1, "us", 0)
            .map { it.podcasts?.toList()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mPodcastDB.podcastDao().insertPodcastsByGenres(it)
            },{
                onError(it.localizedMessage)
            })
    }

    override fun podcastsByGenres(): LiveData<List<PodcastVO>> {
        return mPodcastDB.podcastDao()
            .getPodcastsByGenres()
    }

    override fun getRandomPodcast(): Observable<GetRandomEpisodeResponse> {
        return providePodcastApiService().getRandomPodcastEpisode()
            .subscribeOn(Schedulers.io())
    }

    override fun getDetailsPodcast(id: String): Observable<GetDetailsEpisodeResponse> {
        return providePodcastApiService().getDetailsEpisode(id)
            .subscribeOn(Schedulers.io())
    }

    @SuppressLint("CheckResult")
    override fun getDownloadedPodcastFileAndSaveToDB(onError: (String) -> Unit, audioId: String, audioUrl: String) {
        providePodcastApiService().getDetailsEpisode(audioId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mPodcastDB.podcastDao().insertDownloadPodcast(
                    DownloadedPodcastVO(0, audioId,audioUrl, it)
                )
            },{
                onError(it.localizedMessage)
            })
    }

    override fun getAudioDownloaded(): LiveData<List<DownloadedPodcastVO>> {
        return mPodcastDB.podcastDao()
            .getAudioDownloaded()
    }

    override fun getAudioDownloadedData(audioId: String): LiveData<DownloadedPodcastVO> {
        return mPodcastDB.podcastDao()
            .getDownloadedData(audioId)
    }
}