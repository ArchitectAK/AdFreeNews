package com.cogitator.adfreenews.view.article

import com.cogitator.adfreenews.model.News
import com.cogitator.adfreenews.model.source.NewsRepository
import com.cogitator.adfreenews.model.source.Result
import com.cogitator.adfreenews.utils.launchSilent
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * @author Ankit Kumar on 27/09/2018
 */

class ArticlePresenter(private val newsRepository: NewsRepository,
                       private val uiContext: CoroutineContext = UI) : ArticleContract.Presenter {

    var mView: ArticleContract.View? = null

    override fun attachView(view: ArticleContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    override fun getArticlesByCategory(articleCategory: String) = launchSilent(uiContext) {
        mView?.showLoader()
        val articlesResult = newsRepository.getNewsByCategory(articleCategory)
        when (articlesResult) {
            is Result.Success -> mView?.showArticles(articlesResult.data)
            is Result.Error -> mView?.showError()
        }
    }

    override fun updateBookmarkNews(news: News, position: Int) = launchSilent(uiContext) {
        newsRepository.updateBookmarkStatus(news)
        mView?.changeBookmarkStatus(position)
    }
}