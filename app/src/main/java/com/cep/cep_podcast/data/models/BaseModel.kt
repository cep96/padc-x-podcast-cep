package com.cep.cep_podcast.data.models

import android.content.Context
import com.cep.cep_podcast.network.apis.PodcastApi
import com.cep.cep_podcast.persistence.db.PodcastDB
import com.cep.cep_podcast.utils.API_KEY
import com.cep.cep_podcast.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {

    private lateinit var retrofit: Retrofit
    protected lateinit var mPodcastDB: PodcastDB

//    init {
//        initNetwork()
//    }
//
//    private fun initNetwork() {
//        val okHttpClient = OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .addInterceptor(Interceptor.invoke {chain ->
//                val request = chain.request().newBuilder()
//                    .addHeader("X-ListenAPI-Key", API_KEY)
//                    .build()
//
//                chain.proceed(request)
//            })
//            .build()
//
//        retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//
//    }

    fun providePodcastApiService(): PodcastApi{
        return retrofit.create(PodcastApi::class.java)
    }

    fun initDatabase(context: Context){
        mPodcastDB = PodcastDB.getDBInstance(context)
    }

//    fun init(context: Context){
//        initDatabase(context)
//    }

}