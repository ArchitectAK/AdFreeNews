package com.cogitator.adfreenews.model.source.remote

import com.cogitator.adfreenews.model.News
import com.cogitator.adfreenews.model.source.NewsDataSource
import com.cogitator.adfreenews.model.source.RemoteDataNotFoundException
import com.cogitator.adfreenews.model.source.Result
import com.cogitator.adfreenews.utils.getResult

/**
 * @author Ankit Kumar on 01/10/2018
 */


class NewsRemoteDataSource private constructor(private val newsService: NewsService) : NewsDataSource {


    override suspend fun getNewsById(id: String): Result<News> {
        //no need
        return Result.Error(RemoteDataNotFoundException())
    }

    override suspend fun getNewsByCategory(category: String): Result<ArrayList<News>> {
        val newsResponseResult = newsService.getNews(category).getResult()
        return when (newsResponseResult) {
            is Result.Success -> {
                if (newsResponseResult.data.articles.isNotEmpty())
                    Result.Success(newsResponseResult.data.articles)
                else
                    Result.Error(RemoteDataNotFoundException())
            }
            is Result.Error -> {
                newsResponseResult
            }
        }
    }

    override suspend fun saveNews(news: News) {
        //No need
    }

    override suspend fun updateBookmarkStatus(news: News) {
        //No need
    }

    override suspend fun deleteNews(category: String, isBookmarked: Boolean) {
        //no need
    }

    override suspend fun deleteAllNews() {
        //no need
    }

    override suspend fun getBookmarkedNews(): Result<ArrayList<News>> {
        //no need
        return Result.Error(RemoteDataNotFoundException())
    }

    companion object {

        private lateinit var INSTANCE: NewsRemoteDataSource
        private var needsNewInstance = true

        @JvmStatic
        fun getInstance(newsService: NewsService): NewsRemoteDataSource {
            if (needsNewInstance) {
                INSTANCE = NewsRemoteDataSource(newsService)
                needsNewInstance = false
            }
            return INSTANCE
        }
    }
}