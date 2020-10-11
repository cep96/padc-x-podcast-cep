package com.cep.cep_podcast.network.apis

import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.data.vos.GenreVO
import com.cep.cep_podcast.data.vos.ItemVO
import com.cep.cep_podcast.data.vos.PodcastDetailsVO
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object RealTimeDatabaseFirebaseApiImpl: FirebaseApi {

    private val database: DatabaseReference = Firebase.database.reference

    override fun getPodcasts(
        onSuccess: (List<DataVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("latest_episodes").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val episodeList = arrayListOf<DataVO>()

                snapshot.children.forEach { dataSnapShot ->
                    dataSnapShot.getValue(DataVO::class.java)?.let {
                        episodeList.add(it)
                    }
                }
                onSuccess(episodeList)
            }

        })
    }

    override fun getRandom(
        onSuccess: (List<PodcastDetailsVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("podcasts").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val episodeList = arrayListOf<PodcastDetailsVO>()

                snapshot.children.forEach { dataSnapShot ->
                    dataSnapShot.getValue(PodcastDetailsVO::class.java)?.let {
                        episodeList.add(it)
                    }
                }
                onSuccess(episodeList)
            }

        })
    }

//    override fun getDetails(
//        id: String,
//        onSuccess: (PodcastDetailsVO) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        database.child("podcasts").child(id).addValueEventListener(object : ValueEventListener{
//            override fun onCancelled(error: DatabaseError) {
//                onFailure(error.message)
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val podcastList = arrayListOf<PodcastDetailsVO>()
//
//                snapshot.children.forEach{dataSnapShot ->
//                    dataSnapShot.getValue(PodcastDetailsVO::class.java)?.let{
//                        onSuccess(it)
//                    }
//                }
//
//            }
//
//        })
//    }

    override fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("genres").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val genreList = arrayListOf<GenreVO>()

                snapshot.children.forEach {dataSnapShot ->
                    dataSnapShot.getValue(GenreVO::class.java)?.let {
                        genreList.add(it)
                    }
                }
                onSuccess(genreList)
            }

        })
    }


}