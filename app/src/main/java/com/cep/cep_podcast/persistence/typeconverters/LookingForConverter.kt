package com.cep.cep_podcast.persistence.typeconverters

import androidx.room.TypeConverter
import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.data.vos.LookingForVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LookingForConverter {
    @TypeConverter
    fun toString(data: LookingForVO): String{
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toDataVO(dataStr: String): LookingForVO{
        val dataType = object : TypeToken<LookingForVO>() {}.type
        return Gson().fromJson(dataStr, dataType)
    }

}