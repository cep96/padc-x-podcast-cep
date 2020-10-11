package com.cep.cep_podcast.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "upNextItems")
data class ItemVO(
    var added_at_ms: Long? = 0,
    var data: DataVO? = null,
    @PrimaryKey
    var id: Int? = 0,
    var notes: String? = "",
    var type: String? = ""
)