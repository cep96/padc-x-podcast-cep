package com.cep.cep_podcast

import android.app.Application
import com.cep.cep_podcast.data.models.PodcastModelImpl

class PodcastApp: Application() {

    override fun onCreate() {
        super.onCreate()

        PodcastModelImpl.initDatabase(applicationContext)
    }
}