package com.cep.cep_podcast.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.cep.cep_podcast.data.models.PodcastModelImpl
import com.cep.cep_podcast.mvp.presenters.SearchPresenter
import com.cep.cep_podcast.mvp.views.SearchView
import com.cep.shared.presenters.AbstractBasePresenter

class SearchPresenterImpl: SearchPresenter, AbstractBasePresenter<SearchView>() {

//    val dummyList: ArrayList<DummyPodcast> = arrayListOf(
//        DummyPodcast(1), DummyPodcast(2),
//        DummyPodcast(3), DummyPodcast(5),
//        DummyPodcast(5), DummyPodcast(6),
//        DummyPodcast(7), DummyPodcast(8),
//        DummyPodcast(9), DummyPodcast(10)
//    )
    private val mPodcastModel = PodcastModelImpl
//    private val mPodcastFirebaseModel: PodcastFirebaseModel = PodcastFirebaseModelImpl

    override fun onUIReadyForCategories(lifecycleOwner: LifecycleOwner) {

//        loadCategoryFromApi()
//        requestCategory(lifecycleOwner)
        mPodcastModel.getGenresFromFirebaseAndSaveToDB(onError = {

        })
        mPodcastModel.getGenres()
            .observe(lifecycleOwner, Observer {
                mView?.displayCategories(it)
            })
    }

    private fun requestCategory(lifecycleOwner: LifecycleOwner) {
        mPodcastModel.getGenres()
            .observe(lifecycleOwner, Observer {
                mView?.displayCategories(it)
            })

    }

    private fun loadCategoryFromApi() {
//        mPodcastModel.getGenresFromApiAndSaveToDB (
//            onError = {}
//        )
    }
}