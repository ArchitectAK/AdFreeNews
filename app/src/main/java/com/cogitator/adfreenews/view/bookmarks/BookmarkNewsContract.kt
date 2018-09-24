package com.cogitator.adfreenews.view.bookmarks

import com.cogitator.adfreenews.model.News
import com.cogitator.adfreenews.presenter.BasePresenter
import com.cogitator.adfreenews.view.BaseView

/**
 * @author Ankit Kumar on 24/09/2018
 */
interface BookmarkNewsContract {
    interface View : BaseView {
        fun showLoader()
        fun showArticles(articleList: ArrayList<News>)
        fun removeNewsItem(news: News, position: Int)
        fun showError()
    }

    interface Presenter : BasePresenter<View> {
        fun getBookmarkedArticles()
        fun updateBookmarkStatus(news: News, position: Int)
    }
}