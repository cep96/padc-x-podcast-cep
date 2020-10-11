package com.cep.cep_podcast.data.models

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cep.cep_podcast.data.vos.*
import com.cep.cep_podcast.network.apis.CloudFirestoreDatabaseApiImpl
import com.cep.cep_podcast.network.apis.FirebaseApi
import com.cep.cep_podcast.network.apis.RealTimeDatabaseFirebaseApiImpl
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PodcastModelImpl: PodcastModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = CloudFirestoreDatabaseApiImpl
//    override var mFirebaseApi: FirebaseApi = RealTimeDatabaseFirebaseApiImpl

    override fun getUpNextPodcastFromFirebaseAndSaveToDB(onFailure: (String) -> Unit) {
        mFirebaseApi.getPodcasts(onSuccess = {
            mPodcastDB.podcastDao().insertUpNextItems(it)
        }, onFailure ={

        })
    }

    override fun getUpNextPodcasts(): LiveData<List<DataVO>> {
        return mPodcastDB.podcastDao()
            .getUpNextItems()
    }


//    @SuppressLint("CheckResult")
//    override fun getUpNextPodcastsFromApiAndSaveToDB(onError: (String) -> Unit) {
//        providePodcastApiService().getPlayListsAndItems("m1pe7z60bsw")
//            .map { it.items?.toList() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d("Up Next Items", "==> $it")
//                mPodcastDB.podcastDao().insertUpNextItems(it!!)
//            },{
//                onError(it.localizedMessage)
//            })
//    }


    override fun getGenresFromFirebaseAndSaveToDB(onError: (String) -> Unit) {
        mFirebaseApi.getGenres(onSuccess = {
            mPodcastDB.podcastDao().insertGenres(it)
        }, onFailure = {
            onError(it)
        })
    }

    override fun getGenres(): LiveData<List<GenreVO>> {
        return mPodcastDB.podcastDao()
            .getGenres()
    }


//    @SuppressLint("CheckResult")
//    override fun getGenresFromApiAndSaveToDB(onError: (String) -> Unit) {
//        providePodcastApiService().getPodcastGenres(1)
//            .map { it.genres?.toList() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                mPodcastDB.podcastDao().insertGenres(it!!)
//            },{
//                onError(it.localizedMessage)
//            })
//    }


    override fun getRandomPodcastFromFirebaseAndSaveToDB(onError: (String) -> Unit) {
        mFirebaseApi.getRandom(onSuccess = {
            Log.d("podcastList", "==> $it")
            mPodcastDB.podcastDao().insertRandomPodcast(it)
        }, onFailure = {
            onError(it)
        })
    }

    override fun getRandomPodcast(): LiveData<PodcastDetailsVO> {
        return mPodcastDB.podcastDao()
            .getRamdomPodcast()
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

//    override fun getPodcastDetailsFromFirebaseAndSaveToDB(id: String, onError: (String) -> Unit) {
//        mFirebaseApi.getDetails(id, onSuccess = {
//            mPodcastDB.podcastDao().insertPodcastDetails(it)
//        }, onFailure = {
//            onError(it)
//        })
//    }

    override fun getPodcastDetails(id: String): LiveData<DataVO> {
        return mPodcastDB.podcastDao().getDetails(id)
    }

//    override fun getRandomPodcast(): Observable<GetRandomEpisodeResponse> {
//        return providePodcastApiService().getRandomPodcastEpisode()
//            .subscribeOn(Schedulers.io())
//    }


//    override fun getDetailsPodcast(id: String): Observable<GetDetailsEpisodeResponse> {
//        return providePodcastApiService().getDetailsEpisode(id)
//            .subscribeOn(Schedulers.io())
//    }

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