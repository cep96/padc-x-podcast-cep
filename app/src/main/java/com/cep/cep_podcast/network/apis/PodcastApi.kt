package com.cep.cep_podcast.network.apis

import com.cep.cep_podcast.network.responses.*
import com.cep.cep_podcast.utils.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PodcastApi {

    @GET(GET_RANDOM_PODCAST_EPISODE)
    fun getRandomPodcastEpisode(): Observable<GetRandomEpisodeResponse>

    @GET(GET_PLAY_LISTS_INFO_N_ITEMS)
    fun getPlayListsAndItems(
        @Path("id") id: String
    ): Observable<GetPlayListsInfoAndItemsResponse>

    @GET(GET_DETAILS_EPISODE)
    fun getDetailsEpisode(
        @Path("id") id: String
    ): Observable<GetDetailsEpisodeResponse>

    @GET(GET_PODCAST_GENRES)
    fun getPodcastGenres(
        @Query("top_level_only") topLevelOnly: Int
    ): Observable<GetPodcastGenresResponse>

    @GET(GET_BEST_PODCAST_BY_GENRES)
    fun getBestPodcastByGenres(
        @Query("genre_id") genreId: Int,
        @Query("page") page: Int,
        @Query("region") region: String,
        @Query("safe_mode") safeMode: Int
    ): Observable<GetPodcastListByGenreResponse>
}