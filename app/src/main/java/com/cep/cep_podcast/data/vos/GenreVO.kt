package com.cep.cep_podcast.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "genres")
data class GenreVO(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var name: String = "",
    var parent_id: Int = 0,
    val image: String = ""
)