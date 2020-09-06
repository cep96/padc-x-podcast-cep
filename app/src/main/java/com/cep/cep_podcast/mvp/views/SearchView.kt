package com.cep.cep_podcast.mvp.views

import com.cep.cep_podcast.data.vos.GenreVO

interface SearchView: BaseView {

    fun displayCategories(categories: List<GenreVO>)
}