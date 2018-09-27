package com.cogitator.adfreenews.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Ankit Kumar on 27/09/2018
 */
fun ViewGroup.inflate(layoutRes: Int) = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}