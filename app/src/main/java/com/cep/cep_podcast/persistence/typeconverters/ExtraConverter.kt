package com.cep.cep_podcast.persistence.typeconverters

import androidx.room.TypeConverter
import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.data.vos.ExtraVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExtraConverter {
    @TypeConverter
    fun toString(data: ExtraVO): String{
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toDataVO(dataStr: String): ExtraVO{
        val dataType = object : TypeToken<ExtraVO>() {}.type
        return Gson().fromJson(dataStr, dataType)
    }

}