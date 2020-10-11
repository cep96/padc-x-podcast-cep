package com.cep.shared.presenters

import androidx.lifecycle.ViewModel
import com.cep.shared.views.BaseView

abstract class AbstractBasePresenter<T: BaseView>: BasePresenter<T>, ViewModel() {

    var mView: T ?= null

    override fun initPresenter(view: T) {
        mView = view
    }
}