package com.cogitator.adfreenews.view.newsDetail

import com.cogitator.adfreenews.model.News
import com.cogitator.adfreenews.model.source.NewsRepository
import com.cogitator.adfreenews.model.source.Result
import com.cogitator.adfreenews.utils.launchSilent
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * @author Ankit Kumar on 27/09/2018
 */
class NewsDetailPresenter(private val newsRepository: NewsRepository,
                          private val uiContext: CoroutineContext = UI): NewsDetailContract.Presenter {

    var mView: NewsDetailContract.View? = null

    override fun attachView(view: NewsDetailContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    override fun getArticleById(newsId: String) = launchSilent(uiContext) {
        val result = newsRepository.getNewsById(newsId)
        if (result is Result.Success) {
            mView?.showArticleDetail(result.data)
        }
    }

    override fun updateBookmarkNews(news: News, position: Int) {

    }
}