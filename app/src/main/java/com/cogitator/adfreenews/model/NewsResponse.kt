package com.cogitator.adfreenews.model

/**
 * @author Ankit Kumar on 14/09/2018
 */
data class NewsResponse(
        var status: String,
        var totalResults: Int = 0,
        var articles: ArrayList<News>)