package com.cogitator.adfreenews.view.article

import com.cogitator.adfreenews.model.News
import com.cogitator.adfreenews.presenter.BasePresenter
import com.cogitator.adfreenews.view.BaseView

/**
 * @author Ankit Kumar on 14/09/2018
 */

interface ArticleContract {

    interface View: BaseView {
        fun showLoader()
        fun showArticles(articleList:ArrayList<News>)
        fun changeBookmarkStatus(position: Int)
        fun showError()
    }

    interface Presenter: BasePresenter<View> {
        fun getArticlesByCategory(articleCategory:String)
        fun updateBookmarkNews(news: News, position: Int)
    }
}