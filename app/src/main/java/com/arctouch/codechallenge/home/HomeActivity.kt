package com.arctouch.codechallenge.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.model.Movie
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity(), HomeModule.View {

    private var presenter: HomeModule.Presenter? = null
    private var moviesLiveData: MutableLiveData<List<Movie>>? = MutableLiveData()

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        presenter = HomePresenter(this)
        moviesLiveData?.observe(this, Observer {
            it?.let { setAdapter(it) } ?: run { presenter?.callMoviesApi(1) }
        })
        presenter?.callMoviesApi(1)
    }

    override fun onDestroy() {
        presenter?.unregister()
        presenter = null

        super.onDestroy()
    }

    override fun loadedMovies(movies: List<Movie>) {
        moviesLiveData?.postValue(movies)
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setAdapter(movies: List<Movie>) {
        recyclerView.adapter = HomeAdapter(presenter, movies)
    }
}
