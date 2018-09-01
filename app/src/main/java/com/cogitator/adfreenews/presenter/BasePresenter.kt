package com.cogitator.adfreenews.presenter

import com.cogitator.adfreenews.view.BaseView

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 1/9/18 (MM/DD/YYYY)
 */
interface BasePresenter<in BV : BaseView> {
    fun attachView(view: BV)
    fun detachView()
}