package com.cogitator.adfreenews.utils

import android.content.Context
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator

/**
 * @author Ankit Kumar on 27/09/2018
 */

object AnimUtils {

    var fastOutSlowIn: Interpolator? = null

    fun getFastOutSlowInInterpolator(context: Context): Interpolator {
        return fastOutSlowIn ?: AnimationUtils.loadInterpolator(context,
                android.R.interpolator.fast_out_slow_in).apply { fastOutSlowIn = this }
    }
}