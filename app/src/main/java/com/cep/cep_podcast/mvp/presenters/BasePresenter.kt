package com.cep.cep_podcast.mvp.presenters

import com.cep.cep_podcast.mvp.views.BaseView

interface BasePresenter<T: BaseView> {

    fun initPresenter(view: T)
}