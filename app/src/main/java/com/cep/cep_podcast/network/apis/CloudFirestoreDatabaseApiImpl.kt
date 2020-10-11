package com.cep.cep_podcast.network.apis

import com.cep.cep_podcast.data.vos.*
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object CloudFirestoreDatabaseApiImpl : FirebaseApi {

    val db = Firebase.firestore

    override fun getPodcasts(onSuccess: (List<DataVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("latest_episodes")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check Connection")
                } ?: run {
                    val episodeList: MutableList<DataVO> = arrayListOf()

                    val results = value?.documents ?: arrayListOf()

                    for (document in results) {
                        val data = document.data
                        val episode = DataVO()
                        episode.audio = data?.get("audio") as String
                        episode.audio_length_sec = (data?.get("audio_length_sec") as Long).toInt()
                        episode.description = data?.get("description") as String
                        episode.explicit_content = data?.get("explicit_content") as Boolean
                        episode.id = data?.get("id") as String
                        episode.image = data?.get("image") as String
                        episode.link = data?.get("link") as String
                        episode.listennotes_edit_url = data?.get("listennotes_edit_url") as String
                        episode.listennotes_url = data?.get("listennotes_url") as String
                        episode.maybe_audio_invalid = data["maybe_audio_invalid"] as Boolean
                        episode.title = data["title"] as String

                        val map: Map<String, Any> = data["podcast"] as Map<String, Any>
                        var podcast = PodcastVO("", "", "","", "", "")
                        podcast.id = map["id"] as String
                        podcast.title = map["title"] as String
                        podcast.listennotes_url = map["listennotes_url"] as String
                        podcast.image = map["image"] as String

                        episode.podcast = podcast

                        episodeList.add(episode)
                    }

                    onSuccess(episodeList)
                }
            }
    }

    override fun getRandom(
        onSuccess: (List<PodcastDetailsVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("podcasts")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check Connection")
                } ?: run {
                    val episodeList: MutableList<PodcastDetailsVO> = arrayListOf()
                    val results = value?.documents ?: arrayListOf()

                    for (document in results){
                        val data = document.data
                        val episode = PodcastDetailsVO()
                        episode.id = data?.get("id") as String
                        episode.image = data["image"] as String
                        episode.listennotes_url = data["listennotes_url"] as String
                        episode.title = data["title"] as String
                        episode.description = data["description"] as String
                        episodeList.add(episode)
                    }

                    onSuccess(episodeList)
                }
            }
    }

//    override fun getDetails(
//        id: String,
//        onSuccess: (PodcastDetailsVO) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//    }

    override fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("genres")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check Connection")
                } ?: run {
                    val genreList: MutableList<GenreVO> = arrayListOf()
                    val results = value?.documents ?: arrayListOf()

                    for (document in results) {
                        val data = document.data
                        var genre = GenreVO(0, "", 0, "")

                        genre.id = (data?.get("id") as Long).toInt()
                        genre.name = data?.get("name") as String
                        genre.parent_id = (data?.get("parent_id") as Long).toInt()
                        genreList.add(genre)
                    }

                    onSuccess(genreList)

                }
            }
    }
}