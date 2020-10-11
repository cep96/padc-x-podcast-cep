package com.cep.cep_podcast.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreTypeConverter {
    @TypeConverter
    fun toString(genreList: ArrayList<Int>): String {
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreListJsonString: String): ArrayList<Int> {
        val genreListType = object : TypeToken<ArrayList<Int>>() {}.type
        return Gson().fromJson(genreListJsonString, genreListType)
    }
}