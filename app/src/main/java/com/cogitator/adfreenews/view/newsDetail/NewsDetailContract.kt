package com.cogitator.adfreenews.view.newsDetail

import com.cogitator.adfreenews.model.News
import com.cogitator.adfreenews.presenter.BasePresenter
import com.cogitator.adfreenews.view.BaseView

/**
 * @author Ankit Kumar on 27/09/2018
 */

interface NewsDetailContract {
    interface View : BaseView {
        fun showArticleDetail(news: News)
    }

    interface Presenter : BasePresenter<View> {
        fun getArticleById(newsId: String)
        fun updateBookmarkNews(news: News, position: Int)
    }
}