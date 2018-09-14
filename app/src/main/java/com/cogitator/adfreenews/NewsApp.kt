package com.cogitator.adfreenews

import android.app.Application
import android.support.annotation.NonNull

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 1/9/18 (MM/DD/YYYY)
 */
class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val crashlyticsKit = Crashlytics.Builder()
                .core(CrashlyticsCore . Builder ().disabled(BuildConfig.DEBUG).build())
                .build();
        Fabric.with(this, crashlyticsKit, new Crashlytics ());
    }

}