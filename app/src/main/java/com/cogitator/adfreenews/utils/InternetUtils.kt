package com.cogitator.adfreenews.utils

import android.content.Context
import android.net.ConnectivityManager
import com.cogitator.adfreenews.NewsApp

/**
 * @author Ankit Kumar on 27/09/2018
 */

fun isNetConnected(): Boolean {
    val cm = NewsApp.getInstance().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}