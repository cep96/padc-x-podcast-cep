package com.cep.cep_podcast.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "podcast")
data class PodcastVO(
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var image: String? = "",
    var listennotes_url: String? = "",
    var publisher: String? = "",
    var thumbnail: String? = "",
    var title: String? = ""
)