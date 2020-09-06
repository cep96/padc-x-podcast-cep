package com.cep.cep_podcast.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.cep.cep_podcast.mvp.views.SearchView

interface SearchPresenter: BasePresenter<SearchView> {

    fun onUIReadyForCategories(lifecycleOwner: LifecycleOwner)
}