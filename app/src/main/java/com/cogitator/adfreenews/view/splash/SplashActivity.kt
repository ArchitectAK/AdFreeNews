package com.cogitator.adfreenews.view.splash

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cogitator.adfreenews.R
import com.cogitator.adfreenews.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 1/9/18 (MM/DD/YYYY)
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)
            val ivSet = AnimatorInflater.loadAnimator(this, R.animator.logo_animation) as AnimatorSet
            ivSet.setTarget(splashIv)
            val tvSet = AnimatorInflater.loadAnimator(this, R.animator.logo_animation) as AnimatorSet
            tvSet.setTarget(splashTv)
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(ivSet, tvSet)
            animatorSet.duration = 500
            animatorSet.start()
            animatorSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    proceedForMainScreen()
//                    animatedStartActivity()
                }

                override fun onAnimationCancel(animation: Animator) {
                    proceedForMainScreen()
//                    animatedStartActivity()
                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
        } catch (e: Exception) {
            proceedForMainScreen()
//            animatedStartActivity()
        }
    }

    private fun proceedForMainScreen() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        this@SplashActivity.startActivity(intent)
        this@SplashActivity.finish()
    }

//    private fun animatedStartActivity() {
//        // we only animateOut this activity here.
//        // The new activity will animateIn from its onResume() - be sure to
//        // implement it.
//        val intent = Intent(applicationContext, MainActivity::class.java)
//        // disable default animation for new intent
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//        ActivitySwitcher.animationOut(findViewById<View>(R.id.container_splash),
//                windowManager,
//                object : ActivitySwitcher.AnimationFinishedListener {
//                    override fun onAnimationFinished() {
//                        startActivity(intent)
//                        finish()
//                    }
//                })
//    }
}