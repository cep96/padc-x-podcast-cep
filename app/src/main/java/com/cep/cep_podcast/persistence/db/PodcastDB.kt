package com.cep.cep_podcast.persistence.db

import android.content.Context
import androidx.room.*
import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.cep_podcast.data.vos.GenreVO
import com.cep.cep_podcast.data.vos.ItemVO
import com.cep.cep_podcast.data.vos.PodcastVO
import com.cep.cep_podcast.persistence.daos.PodcastDao
import com.cep.cep_podcast.persistence.typeconverters.DataConverter
import com.cep.cep_podcast.persistence.typeconverters.DetailsConverter

@Database(entities = [PodcastVO::class, ItemVO::class, GenreVO::class, DownloadedPodcastVO::class], version = 2, exportSchema = false)
@TypeConverters(DataConverter::class, DetailsConverter::class)
abstract class PodcastDB: RoomDatabase() {

    companion object{
        const val DB_NAME = "Podcast.db"

        var dbInstance: PodcastDB ?= null

        fun getDBInstance(context: Context): PodcastDB{
            when(dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context, PodcastDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            val i = dbInstance
            return i!!
        }
    }

    abstract fun podcastDao(): PodcastDao
}