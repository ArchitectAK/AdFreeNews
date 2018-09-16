package com.cogitator.adfreenews.model.source

/**
 * @author Ankit Kumar on 14/09/2018
 */

sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: T) : Result<T>()

    class Error(val exception: Throwable?) : Result<Nothing>()
}