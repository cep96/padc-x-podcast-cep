package com.cep.cep_podcast.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upNextItems")
data class ItemVO(
    val added_at_ms: Long,
    val data: DataVO,
    @PrimaryKey
    val id: Int,
    val notes: String,
    val type: String
)