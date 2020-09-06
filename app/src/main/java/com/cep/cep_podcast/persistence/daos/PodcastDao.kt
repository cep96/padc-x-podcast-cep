package com.cep.cep_podcast.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.cep_podcast.data.vos.GenreVO
import com.cep.cep_podcast.data.vos.ItemVO
import com.cep.cep_podcast.data.vos.PodcastVO

@Dao
interface PodcastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpNextItems(items: List<ItemVO>)

    @Query("SELECT * FROM upNextItems")
    fun getUpNextItems(): LiveData<List<ItemVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genres: List<GenreVO>)

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

}