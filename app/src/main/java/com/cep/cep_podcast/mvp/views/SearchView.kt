package com.cep.cep_podcast.mvp.views

import com.cep.cep_podcast.data.vos.GenreVO
import com.cep.shared.views.BaseView

interface SearchView: BaseView {

    fun displayCategories(categories: List<GenreVO>)
    fun showErrorMessage(message: String)
}