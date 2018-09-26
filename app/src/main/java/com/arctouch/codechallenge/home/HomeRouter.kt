package com.arctouch.codechallenge.home

import android.app.Activity
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.util.buildMovieDetailIntent

class HomeRouter(var activity: Activity?) : HomeModule.Router {

    override fun goToDetailScreen(movie: Movie) {
        activity?.startActivity(activity?.buildMovieDetailIntent(movie))
    }

    override fun unregister() {
        activity = null
    }

}