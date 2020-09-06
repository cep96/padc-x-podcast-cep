package com.cep.cep_podcast.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.cep.cep_podcast.data.dummydata.DummyPodcast
import com.cep.cep_podcast.data.models.PodcastModelImpl
import com.cep.cep_podcast.mvp.presenters.AbstractBasePresenter
import com.cep.cep_podcast.mvp.presenters.SearchPresenter
import com.cep.cep_podcast.mvp.views.SearchView

class SearchPresenterImpl: SearchPresenter, AbstractBasePresenter<SearchView>() {

//    val dummyList: ArrayList<DummyPodcast> = arrayListOf(
//        DummyPodcast(1), DummyPodcast(2),
//        DummyPodcast(3), DummyPodcast(5),
//        DummyPodcast(5), DummyPodcast(6),
//        DummyPodcast(7), DummyPodcast(8),
//        DummyPodcast(9), DummyPodcast(10)
//    )
    private val mPodcastModel = PodcastModelImpl

    override fun onUIReadyForCategories(lifecycleOwner: LifecycleOwner) {
//        mView?.displayCategories(dummyList)
        loadCategoryFromApi()
        requestCategory(lifecycleOwner)
    }

    private fun requestCategory(lifecycleOwner: LifecycleOwner) {
        mPodcastModel.getGenres()
            .observe(lifecycleOwner, Observer {
                mView?.displayCategories(it)
            })

    }

    private fun loadCategoryFromApi() {
        mPodcastModel.getGenresFromApiAndSaveToDB (
            onError = {}
        )
    }
}