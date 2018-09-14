package com.cogitator.adfreenews.model.source

import com.cogitator.adfreenews.model.News

/**
 * @author Ankit Kumar on 14/09/2018
 */

interface NewsDataSource {

    suspend fun getNewsById(id: String): Result<News>

    suspend fun getNewsByCategory(category: String): Result<ArrayList<News>>

    suspend fun getBookmarkedNews(): Result<ArrayList<News>>

    suspend fun saveNews(news: News)

    suspend fun updateBookmarkStatus(news: News)

    suspend fun deleteNews(category: String,isBookmarked: Boolean)

    suspend fun deleteAllNews()

}