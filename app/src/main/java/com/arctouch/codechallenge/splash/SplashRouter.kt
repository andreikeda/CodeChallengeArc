package com.arctouch.codechallenge.splash

import android.app.Activity
import com.arctouch.codechallenge.util.buildHomeIntent

class SplashRouter(var activity: Activity?) : SplashModule.Router {

    override fun goToHomeScreen() {
        activity?.startActivity(activity?.buildHomeIntent())
    }

    override fun unregister() {
        activity = null
    }

}