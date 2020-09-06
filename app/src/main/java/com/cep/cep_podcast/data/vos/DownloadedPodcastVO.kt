package com.cep.cep_podcast.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse

@Entity (tableName = "downloaded")
data class DownloadedPodcastVO (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val audioId: String,
    val audioUrl: String,
    val audioDetails: GetDetailsEpisodeResponse
)