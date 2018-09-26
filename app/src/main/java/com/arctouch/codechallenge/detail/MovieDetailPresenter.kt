package com.arctouch.codechallenge.detail

import com.arctouch.codechallenge.model.Movie

class MovieDetailPresenter(var view: MovieDetailModule.View?) : MovieDetailModule.Presenter {

    override fun onActivityCreated(movie: Movie) {
        movie.backdropPath?.let { view?.setBackdrop(it) }
        movie.genres?.let {  }
        movie.overview?.let { view?.setOverview(it) }
        movie.posterPath?.let { view?.setPoster(it) }
        movie.releaseDate?.let { view?.setReleaseDate(it) }
        view?.setTitle(movie.title)
    }

    override fun unregister() {
        view = null
    }

}