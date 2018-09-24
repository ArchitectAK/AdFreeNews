package com.cogitator.adfreenews.view.bookmarks

import com.cogitator.adfreenews.model.News
import com.cogitator.adfreenews.model.source.NewsRepository
import com.cogitator.adfreenews.model.source.Result
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * @author Ankit Kumar on 24/09/2018
 */
class BookmarkNewsPresenter(private val newsRepository: NewsRepository,
                            private val uiContext: CoroutineContext = UI) : BookmarkNewsContract.Presenter {

    var mView: BookmarkNewsContract.View? = null
    override fun attachView(view: BookmarkNewsContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    override fun getBookmarkedArticles() = launchSilent(uiContext) {
        mView?.showLoader()
        val bookmarkedNewsResult = newsRepository.getBookmarkedNews()
        when (bookmarkedNewsResult) {
            is Result.Success -> mView?.showArticles(bookmarkedNewsResult.data)
            is Result.Error -> mView?.showError()
        }
    }

    override fun updateBookmarkStatus(news: News, position: Int) = launchSilent(uiContext) {
        newsRepository.updateBookmarkStatus(news)
        mView?.removeNewsItem(news, position)
    }
}