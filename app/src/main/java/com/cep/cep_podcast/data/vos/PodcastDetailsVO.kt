package com.cep.cep_podcast.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "podcastDetails")
data class PodcastDetailsVO(
    var country: String = "",
    var description: String = "",
    var earliest_pub_date_ms: Long = 0,
    var email: String = "",
    var explicit_content: Boolean = false,
    var extra: ExtraVO = ExtraVO(),
    var genre_ids: ArrayList<Int> = arrayListOf(),
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var image: String = "",
    var is_claimed: Boolean = false,
    var itunes_id: Int = 0,
    var language: String = "",
    var latest_pub_date_ms: Long = 0,
    var listennotes_url: String = "",
    var looking_for: LookingForVO = LookingForVO(),
    var publisher: String = "",
    var rss: String = "",
    var thumbnail: String = "",
    var title: String = "",
    var total_episodes: Int = 0,
    var type: String = "",
    var website: String = ""
)