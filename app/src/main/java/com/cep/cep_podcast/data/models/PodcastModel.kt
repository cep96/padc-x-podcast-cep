package com.cep.cep_podcast.data.models

import androidx.lifecycle.LiveData
import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.cep_podcast.data.vos.GenreVO
import com.cep.cep_podcast.data.vos.ItemVO
import com.cep.cep_podcast.data.vos.PodcastVO
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse
import io.reactivex.Observable

interface PodcastModel {

    fun getUpNextPodcastsFromApiAndSaveToDB(onError: (String) -> Unit)
    fun getUpNextPodcasts(): LiveData<List<ItemVO>>

    fun getGenresFromApiAndSaveToDB(onError: (String) -> Unit)
    fun getGenres(): LiveData<List<GenreVO>>

    fun getPodcastsByGenreFromApiAndSaveToDB(genreId: Int, onError: (String) -> Unit)
    fun podcastsByGenres(): LiveData<List<PodcastVO>>

    fun getRandomPodcast(): Observable<GetRandomEpisodeResponse>

    fun getDetailsPodcast(id: String): Observable<GetDetailsEpisodeResponse>

    fun getDownloadedPodcastFileAndSaveToDB(onError: (String) -> Unit, audioId: String, audioUrl: String)
    fun getAudioDownloaded(): LiveData<List<DownloadedPodcastVO>>

    fun getAudioDownloadedData(audioId: String): LiveData<DownloadedPodcastVO>
}