package com.cep.cep_podcast.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreVO(
    @PrimaryKey
    val id: Int,
    val name: String,
    val parent_id: Int,
    val image: String
)