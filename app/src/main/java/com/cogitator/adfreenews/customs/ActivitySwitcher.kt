package com.cogitator.adfreenews.customs

import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation


/**
 * @author Ankit Kumar on 08/10/2018
 */
object ActivitySwitcher {

    private val DURATION: Int = 500
    private val DEPTH = 400.0f

    /* ----------------------------------------------- */

    interface AnimationFinishedListener {
        /**
         * Called when the animation is finished.
         */
        fun onAnimationFinished()
    }

    /* ----------------------------------------------- */

    fun animationIn(container: View, windowManager: WindowManager) {
        animationIn(container, windowManager, null)
    }

    fun animationIn(container: View, windowManager: WindowManager, listener: AnimationFinishedListener?) {
        apply3DRotation(90F, 0F, false, container, windowManager, listener)
    }

    fun animationOut(container: View, windowManager: WindowManager) {
        animationOut(container, windowManager, null)
    }

    fun animationOut(container: View, windowManager: WindowManager, listener: AnimationFinishedListener?) {
        apply3DRotation(0F, -360F, true, container, windowManager, listener)
    }
    /* ----------------------------------------------- */

    private fun apply3DRotation(fromDegree: Float, toDegree: Float,
                                reverse: Boolean, container: View, windowManager: WindowManager,
                                listener: AnimationFinishedListener?) {
        val display = windowManager.defaultDisplay
        val centerX = display.width / 2.0f
        val centerY = display.height / 2.0f

        val a = Rotate3dAnimation(fromDegree, toDegree,
                centerX, centerY, DEPTH, reverse)
        a.reset()
        a.duration = DURATION.toLong()
        a.fillAfter = true
        a.interpolator = AccelerateInterpolator()
        if (listener != null) {
            a.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationRepeat(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    listener.onAnimationFinished()
                }
            })
        }
        container.clearAnimation()
        container.startAnimation(a)
    }
}