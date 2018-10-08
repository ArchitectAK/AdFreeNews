package com.cogitator.adfreenews

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 1/9/18 (MM/DD/YYYY)
 */
class NewsApp : Application() {
    companion object {
        internal lateinit var Instance: NewsApp
        fun getInstance(): NewsApp {
            return Instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        Instance = this
        val crashlyticsKit = Crashlytics.Builder()
                .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build()
        Fabric.with(this, crashlyticsKit, Crashlytics())
    }


}