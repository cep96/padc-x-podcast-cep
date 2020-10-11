package com.cep.cep_podcast.persistence.typeconverters

import androidx.room.TypeConverter
import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.data.vos.PodcastVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PodcastConverter {
    @TypeConverter
    fun toString(data: PodcastVO): String{
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toPodcastVO(dataStr: String): PodcastVO{
        val dataType = object : TypeToken<PodcastVO>() {}.type
        return Gson().fromJson(dataStr, dataType)
    }

}