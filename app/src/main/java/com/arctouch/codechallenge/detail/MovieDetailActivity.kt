package com.arctouch.codechallenge.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.util.EXTRA_MOVIE

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie
        Log.d("Teste", movie.title)
    }

}