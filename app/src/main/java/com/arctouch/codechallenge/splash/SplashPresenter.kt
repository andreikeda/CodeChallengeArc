package com.arctouch.codechallenge.splash

import android.app.Activity
import android.app.Application

class SplashPresenter(var view : SplashModule.View?) : SplashModule.Presenter, SplashModule.InteractorOutput {

    private var interactor : SplashModule.Interactor? = SplashInteractor(this)
    private var router : SplashModule.Router? = SplashRouter(view as Activity)

    override fun loadedGenresError(errorMessage: String) {
        view?.showError(errorMessage)
    }

    override fun loadedGenresSuccess() {
        router?.goToHomeScreen()
    }

    override fun callGenresApi(application: Application) {
        interactor?.loadGenres(application)
    }

    override fun unregister() {
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
        view = null
    }

}