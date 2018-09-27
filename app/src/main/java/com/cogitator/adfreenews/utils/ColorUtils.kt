package com.cogitator.adfreenews.utils

import android.support.annotation.IntDef
import android.support.annotation.FloatRange
import android.support.annotation.ColorInt
import android.support.v7.graphics.Palette
import android.graphics.Bitmap
import android.support.annotation.NonNull
import android.support.annotation.CheckResult
import java.lang.annotation.RetentionPolicy


/**
 * @author Ankit Kumar on 27/09/2018
 */
class ColorUtils{
    private fun ColorUtils(): ??? {}

    val IS_LIGHT = 0
    val IS_DARK = 1
    val LIGHTNESS_UNKNOWN = 2

    /**
     * Set the alpha component of `color` to be `alpha`.
     */
    @CheckResult
    @ColorInt
    fun modifyAlpha(@ColorInt color: Int,
                    @IntRange(from = 0, to = 255) alpha: Int): Int {
        return color and 0x00ffffff or (alpha shl 24)
    }

    /**
     * Checks if the most populous color in the given palette is dark
     *
     *
     */
    @Lightness
    fun isDark(palette: Palette): Int {
        val mostPopulous = getMostPopulousSwatch(palette) ?: return LIGHTNESS_UNKNOWN
        return if (isDark(mostPopulous.rgb)) IS_DARK else IS_LIGHT
    }

    @Nullable
    fun getMostPopulousSwatch(palette: Palette?): Palette.Swatch? {
        var mostPopulous: Palette.Swatch? = null
        if (palette != null) {
            for (swatch in palette.swatches) {
                if (mostPopulous == null || swatch.population > mostPopulous.population) {
                    mostPopulous = swatch
                }
            }
        }
        return mostPopulous
    }

    /**
     * Determines if a given bitmap is dark. This extracts a palette inline so should not be called
     * with a large image!! If palette fails then check the color of the specified pixel
     */
    fun isDark(bitmap: Bitmap, backupPixelX: Int, backupPixelY: Int): Boolean {
        // first try palette with a small color quant size
        val palette = Palette.from(bitmap).maximumColorCount(3).generate()
        return if (palette != null && palette.swatches.size > 0) {
            isDark(palette) == IS_DARK
        } else {
            // if palette failed, then check the color of the specified pixel
            isDark(bitmap.getPixel(backupPixelX, backupPixelY))
        }
    }

    fun isDark(@ColorInt color: Int): Boolean {
        return android.support.v4.graphics.ColorUtils.calculateLuminance(color) < 0.5
    }

    /**
     * Calculate a variant of the color to make it more suitable for overlaying information. Light
     * colors will be lightened and dark colors will be darkened
     *
     * @param color the color to adjust
     * @param isDark whether `color` is light or dark
     * @param lightnessMultiplier the amount to modify the color e.g. 0.1f will alter it by 10%
     * @return the adjusted color
     */
    @ColorInt
    fun scrimify(@ColorInt color: Int,
                 isDark: Boolean,
                 @FloatRange(from = 0.0, to = 1.0) lightnessMultiplier: Float): Int {
        var lightnessMultiplier = lightnessMultiplier
        val hsl = FloatArray(3)
        android.support.v4.graphics.ColorUtils.colorToHSL(color, hsl)

        if (!isDark) {
            lightnessMultiplier += 1f
        } else {
            lightnessMultiplier = 1f - lightnessMultiplier
        }

        hsl[2] = MathUtils.constrain(0f, 1f, hsl[2] * lightnessMultiplier)
        return android.support.v4.graphics.ColorUtils.HSLToColor(hsl)
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(IS_LIGHT, IS_DARK, LIGHTNESS_UNKNOWN)
    annotation class Lightness
}