package com.cogitator.adfreenews.model

/**
 * @author Ankit Kumar on 14/09/2018
 */
class NewsResponse {
    lateinit var status: String
    var totalResults: Int = 0
    lateinit var articles: ArrayList<News>
}