package com.cep.shared.presenters

import com.cep.shared.views.BaseView

interface BasePresenter<T: BaseView> {

    fun initPresenter(view: T)
}