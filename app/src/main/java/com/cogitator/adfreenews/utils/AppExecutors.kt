package com.cogitator.adfreenews.utils

import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlin.coroutines.experimental.CoroutineContext

/**
 * @author Ankit Kumar on 27/09/2018
 */

const val THREAD_COUNT = 3

open class AppExecutors constructor(
        val ioContext: CoroutineContext = DefaultDispatcher,
        val networkContext: CoroutineContext = newFixedThreadPoolContext(THREAD_COUNT, "networkIO"),
        val uiContext: CoroutineContext = UI)