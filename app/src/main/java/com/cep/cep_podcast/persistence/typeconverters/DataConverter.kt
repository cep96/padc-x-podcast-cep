package com.cep.cep_podcast.persistence.typeconverters

import androidx.room.TypeConverter
import com.cep.cep_podcast.data.vos.DataVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun toString(data: DataVO): String{
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toDataVO(dataStr: String): DataVO{
        val dataType = object : TypeToken<DataVO>() {}.type
        return Gson().fromJson(dataStr, dataType)
    }

}