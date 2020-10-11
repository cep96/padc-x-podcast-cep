package com.cep.cep_podcast.data.models

import androidx.lifecycle.LiveData
import com.cep.cep_podcast.data.vos.*
import com.cep.cep_podcast.network.apis.FirebaseApi
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse
import io.reactivex.Observable

interface PodcastModel {

    var mFirebaseApi: FirebaseApi

//    fun getUpNextPodcastsFromApiAndSaveToDB(onError: (String) -> Unit)
    fun getUpNextPodcastFromFirebaseAndSaveToDB(onFailure: (String) -> Unit)
    fun getUpNextPodcasts(): LiveData<List<DataVO>>

//    fun getGenresFromApiAndSaveToDB(onError: (String) -> Unit)
    fun getGenresFromFirebaseAndSaveToDB(onError: (String) -> Unit)
    fun getGenres(): LiveData<List<GenreVO>>

    fun getRandomPodcastFromFirebaseAndSaveToDB(onError: (String) -> Unit)
    fun getRandomPodcast(): LiveData<PodcastDetailsVO>

    fun getPodcastsByGenreFromApiAndSaveToDB(genreId: Int, onError: (String) -> Unit)
    fun podcastsByGenres(): LiveData<List<PodcastVO>>

//    fun getRandomPodcast(): Observable<GetRandomEpisodeResponse>

//    fun getPodcastDetailsFromFirebaseAndSaveToDB(id: String, onError: (String) -> Unit)
    fun getPodcastDetails(id: String): LiveData<DataVO>

    //    fun getDetailsPodcast(id: String): Observable<GetDetailsEpisodeResponse>

    fun getDownloadedPodcastFileAndSaveToDB(onError: (String) -> Unit, audioId: String, audioUrl: String)
    fun getAudioDownloaded(): LiveData<List<DownloadedPodcastVO>>

    fun getAudioDownloadedData(audioId: String): LiveData<DownloadedPodcastVO>


}