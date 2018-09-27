package com.cogitator.adfreenews.utils

import java.util.*

/**
 * @author Ankit Kumar on 27/09/2018
 */

fun constrain(min: Float, max: Float, v: Float) = v.coerceIn(min, max)

fun isTimeGt1Hr(timestamp: Long): Boolean {
    return Math.abs(Date().time - timestamp) > 60 * 60 * 1000
}