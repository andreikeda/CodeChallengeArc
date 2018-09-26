package com.arctouch.codechallenge.home

import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.util.api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeInteractor(var output : HomeModule.InteractorOutput?) : HomeModule.Interactor {

    override fun loadMovies(page: Long) {
        api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page, TmdbApi.DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    output?.loadedMoviesError(it.localizedMessage)
                }
                .subscribe {
                    output?.loadedMoviesSuccess(it)
                }
    }

    override fun unregister() {
        output = null
    }

}