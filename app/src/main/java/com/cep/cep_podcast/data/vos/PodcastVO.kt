package com.cep.cep_podcast.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "podcast")
data class PodcastVO(
    @PrimaryKey
    val id: String,
    val image: String,
    val listennotes_url: String,
    val publisher: String,
    val thumbnail: String,
    val title: String
)