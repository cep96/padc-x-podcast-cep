package com.cep.cep_podcast.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.cep.cep_podcast.mvp.views.SearchView
import com.cep.shared.presenters.BasePresenter

interface SearchPresenter: BasePresenter<SearchView> {

    fun onUIReadyForCategories(lifecycleOwner: LifecycleOwner)
}