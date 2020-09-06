package com.cep.cep_podcast.persistence.typeconverters

import androidx.room.TypeConverter
import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailsConverter {
    @TypeConverter
    fun toString(data: GetDetailsEpisodeResponse): String{
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toDetailsVO(dataStr: String): GetDetailsEpisodeResponse{
        val dataType = object : TypeToken<GetDetailsEpisodeResponse>() {}.type
        return Gson().fromJson(dataStr, dataType)
    }

}