package com.cogitator.adfreenews

import android.content.Context
import com.cogitator.adfreenews.model.source.local.NewsDatabase
import com.cogitator.adfreenews.model.source.local.NewsLocalDataSource
import com.cogitator.adfreenews.model.source.NewsRepository
import com.cogitator.adfreenews.model.source.remote.NewsRemoteDataSource
import com.cogitator.adfreenews.model.source.remote.RestProvider
import com.cogitator.adfreenews.utils.AppExecutors

/**
 * @author Ankit Kumar on 28/09/2018
 */

object Injection {
    fun provideNewsRepository(context: Context): NewsRepository {
        val database = NewsDatabase.getInstance(context)
        return NewsRepository.getInstance(NewsRemoteDataSource.getInstance(RestProvider.getNewsService()),
                NewsLocalDataSource.getInstance(AppExecutors(), database.newsDao()))
    }
}