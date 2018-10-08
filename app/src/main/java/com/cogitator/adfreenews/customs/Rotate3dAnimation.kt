package com.cogitator.adfreenews.customs

import android.graphics.Camera
import android.view.animation.Animation
import android.view.animation.Transformation


/**
 * @author Ankit Kumar on 08/10/2018
 */
class Rotate3dAnimation(val fromDegrees: Float, val toDegrees: Float, val centerX: Float,
                        val centerY: Float, val depthZ: Float, val reverse: Boolean) : Animation() {

    private var mCamera: Camera? = null

    /**
     * Creates a new 3D rotation on the Y axis. The rotation is defined by its
     * start angle and its end angle. Both angles are in degrees. The rotation
     * is performed around a center point on the 2D space, definied by a pair of
     * X and Y coordinates, called centerX and centerY. When the animation
     * starts, a translation on the Z axis (depth) is performed. The length of
     * the translation can be specified, as well as whether the translation
     * should be reversed in time.
     *
     * @param fromDegrees
     *            the start angle of the 3D rotation
     * @param toDegrees
     *            the end angle of the 3D rotation
     * @param centerX
     *            the X center of the 3D rotation
     * @param centerY
     *            the Y center of the 3D rotation
     * @param reverse
     *            true if the translation should be reversed, false otherwise
     */


    init {
        mCamera = Camera()
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mCamera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val fromDegrees = fromDegrees
        val degrees = fromDegrees + (toDegrees - fromDegrees) * interpolatedTime

        val centerX = centerX
        val centerY = centerY
        val camera = mCamera

        val matrix = t.matrix

        camera?.save()
        if (reverse) {
            camera?.translate(0.0f, 0.0f, depthZ * interpolatedTime)
        } else {
            camera?.translate(0.0f, 0.0f, depthZ * (1.0f - interpolatedTime))
        }
        camera?.rotateY(degrees)
        camera?.getMatrix(matrix)
        camera?.restore()

        matrix?.preTranslate(-centerX, -centerY)
        matrix?.postTranslate(centerX, centerY)
    }
}