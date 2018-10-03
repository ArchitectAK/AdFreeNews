package com.cogitator.adfreenews.model.source.remote

import retrofit2.Call
import com.cogitator.adfreenews.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ankit Kumar on 01/10/2018
 */

interface NewsService {

    @GET("top-headlines")
    fun getNews(@Query("category") category: String,
                @Query("country") country: String = "in",
                @Query("pageSize") pageSize: Int = 20): Call<NewsResponse>
}