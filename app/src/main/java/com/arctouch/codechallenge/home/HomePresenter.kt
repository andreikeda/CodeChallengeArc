package com.arctouch.codechallenge.home

import android.app.Activity
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.model.UpcomingMoviesResponse

class HomePresenter(var view : HomeModule.View?) : HomeModule.Presenter, HomeModule.InteractorOutput {

    private var interactor: HomeModule.Interactor? = HomeInteractor(this)
    private var router: HomeModule.Router? = HomeRouter(view as Activity)

    override fun loadedMoviesError(errorMessage: String) {
        view?.showError(errorMessage)
    }

    override fun loadedMoviesSuccess(response: UpcomingMoviesResponse) {
        val moviesWithGenres = response.results.map { movie ->
            movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
        }
        view?.setAdapter(moviesWithGenres)
        view?.hideLoading()
    }

    override fun callMoviesApi(page: Long) {
        view?.showLoading()
        interactor?.loadMovies(page)
    }

    override fun unregister() {
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
        view = null
    }

}