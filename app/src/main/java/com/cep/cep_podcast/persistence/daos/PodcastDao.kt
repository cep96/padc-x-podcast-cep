package com.cep.cep_podcast.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cep.cep_podcast.data.vos.*

@Dao
interface PodcastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpNextItems(items: List<DataVO>)

    @Query("SELECT * FROM dataVO")
    fun getUpNextItems(): LiveData<List<DataVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genres: List<GenreVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRandomPodcast(podcast: List<PodcastDetailsVO>)

    @Query("SELECT * FROM podcastDetails ORDER BY RANDOM()")
    fun getRamdomPodcast(): LiveData<PodcastDetailsVO>

    @Query("SELECT * FROM genres")
    fun getGenres() : LiveData<List<GenreVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPodcastsByGenres(podcasts: List<PodcastVO>)

    @Query("SELECT * FROM podcast")
    fun getPodcastsByGenres(): LiveData<List<PodcastVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDownloadPodcast(podcast: DownloadedPodcastVO)

    @Query("SELECT * FROM downloaded")
    fun getAudioDownloaded(): LiveData<List<DownloadedPodcastVO>>

    @Query("SELECT * FROM downloaded WHERE audioId = :audioId")
    fun getDownloadedData(audioId: String): LiveData<DownloadedPodcastVO>

    @Query("SELECT * FROM podcastDetails WHERE id= :podcastId")
    fun getPodcastDetails(podcastId: String): LiveData<PodcastDetailsVO>

    @Query("SELECT * FROM dataVO WHERE id= :podcastId")
    fun getDetails(podcastId: String): LiveData<DataVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPodcastDetails(podcastDetails: PodcastDetailsVO)

}