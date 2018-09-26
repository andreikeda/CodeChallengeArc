package com.arctouch.codechallenge.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.util.EXTRA_MOVIE
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.detail_activity.*

class MovieDetailActivity : AppCompatActivity(), MovieDetailModule.View {

    private val movieImageUrlBuilder = MovieImageUrlBuilder()
    private var presenter : MovieDetailModule.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        presenter = MovieDetailPresenter(this)
        presenter?.onActivityCreated(intent.getSerializableExtra(EXTRA_MOVIE) as Movie)
    }

    override fun onDestroy() {
        presenter?.unregister()
        presenter = null

        super.onDestroy()
    }

    override fun setBackdrop(backdropUrl: String) {
        Glide.with(this)
                .load(movieImageUrlBuilder.buildPosterUrl(backdropUrl))
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(iv_backdrop)
    }

    override fun setGenres(genres: String) {
        tv_genres.text = genres
    }

    override fun setOverview(overview: String) {
        tv_overview.text = overview
    }

    override fun setPoster(posterUrl: String) {
        Glide.with(this)
                .load(movieImageUrlBuilder.buildPosterUrl(posterUrl))
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(iv_poster)
    }

    override fun setReleaseDate(releaseDate: String) {
        tv_release_date.text = releaseDate
    }

    override fun setTitle(name: String) {
        tv_name.text = name
        title = name
    }

}