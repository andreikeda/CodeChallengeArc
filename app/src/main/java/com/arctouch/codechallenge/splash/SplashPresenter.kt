package com.arctouch.codechallenge.splash

import android.app.Activity
import com.arctouch.codechallenge.model.Genre

class SplashPresenter(var view : SplashModule.View?) : SplashModule.Presenter, SplashModule.InteractorOutput {

    private var interactor : SplashModule.Interactor? = SplashInteractor(this)
    private var router : SplashModule.Router? = SplashRouter(view as Activity)

    override fun loadedGenresError(errorMessage: String) {
        view?.showError(errorMessage)
    }

    override fun loadedGenresSuccess(genres: List<Genre>) {
        view?.loadedGenres(genres)
    }

    override fun callGenresApi() {
        interactor?.loadGenres()
    }

    override fun unregister() {
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
        view = null
    }

}