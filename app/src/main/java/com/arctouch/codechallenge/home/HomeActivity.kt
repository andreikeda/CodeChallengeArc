package com.arctouch.codechallenge.home

import android.app.SearchManager
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.model.Movie
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity(), HomeModule.View {

    private var presenter: HomeModule.Presenter? = null
    private val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    private val pageLiveData: MutableLiveData<Long> = MutableLiveData()
    private val hasMorePagesLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        val manager = getSystemService(SEARCH_SERVICE) as SearchManager
        val search = menu.findItem(R.id.search).actionView as SearchView
        search.setSearchableInfo(manager.getSearchableInfo(componentName))
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // LOAD HISTORY
                return true
            }
        })
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        presenter = HomePresenter(this)

        moviesLiveData.observe(this, Observer {
            it?.let { setAdapter(it) } ?: run { searchForPage() }
        })

        searchForPage()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val shallLoadContent : Boolean = hasMorePagesLiveData.value?.let { it } ?: run { true }
                when (shallLoadContent) {
                    true -> {
                        val layoutManager = recyclerView?.layoutManager as LinearLayoutManager
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                        if (visibleItemCount + pastVisibleItems >= totalItemCount && !progressBar.isShown) {
                            searchForPage()
                        }
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        presenter?.unregister()
        presenter = null

        super.onDestroy()
    }

    override fun loadedMovies(movies: List<Movie>) {
        moviesLiveData.postValue(movies)
    }

    override fun setPage(page: Long) {
        pageLiveData.value = page
    }

    override fun setHasMorePages(hasMorePages: Boolean) {
        hasMorePagesLiveData.value = hasMorePages
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun searchForPage() {
        val currentPage : Long = pageLiveData.value?.let { it + 1 } ?: run { 1L }
        presenter?.callMoviesApi(currentPage)
    }

    private fun setAdapter(movies: List<Movie>) {
        when (recyclerView.adapter) {
            null -> recyclerView.adapter = HomeAdapter(presenter, movies.toMutableList())
            else -> {
                (recyclerView.adapter as HomeAdapter).addData(movies)
                recyclerView.adapter.notifyDataSetChanged()
            }
        }
    }
}
