package com.cep.cep_podcast.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "dataVO")
data class DataVO(
    var audio: String? = "",
    var audio_length_sec: Int? = 0,
    var description: String? = "",
    var explicit_content: Boolean? = false,
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var image: String? = "",
    var link: String? = "",
    var listennotes_edit_url: String? = "",
    var listennotes_url: String? = "",
    var maybe_audio_invalid: Boolean? = false,
    var podcast: PodcastVO = PodcastVO(),
    var pub_date_ms: Long? = 0,
    var thumbnail: String? = "",
    var title: String? = ""
)