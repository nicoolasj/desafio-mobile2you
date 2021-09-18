package com.desafio.mobile2you.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.desafio.mobile2you.R
import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.databinding.ActivityMainBinding
import com.desafio.mobile2you.presentation.adapter.SimilarMovieAdapter
import com.desafio.mobile2you.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: SimilarMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        displayMovie()
        displaySimilarMovies()

        val sharedPref = getSharedPreferences("pref", MODE_PRIVATE)
        binding.favoriteButton.setOnCheckedChangeListener { _, isChecked ->
            sharedPref.edit().apply {
                putBoolean("state", isChecked)
                apply()
            }
        }

        binding.favoriteButton.isChecked = sharedPref.getBoolean("state", false)
    }

    private fun similarMoviesRecyclerViewInit() {
        adapter = SimilarMovieAdapter()
        binding.similarMoviesRecyclerView.adapter = adapter
        binding.similarMoviesRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun bindMovie(movie: Movie) {
        binding.movieTitle.text = movie.title
        binding.likeCount.text = getString(R.string.like_count, movie.voteCount.toString())
        binding.popularityCount.text = getString(R.string.popularity_count, movie.popularity.toString())
        binding.movieDescription.text = movie.overview
        binding.genreNames.text = "${movie.genres.map { genre -> genre.name }}"
        Glide.with(binding.moviePoster)
            .load("https://image.tmdb.org/t/p/original/${movie.posterPath}")
            .into(binding.moviePoster)
    }

    private fun displayMovie() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getMovie().observe(this, {
            Log.d(TAG, "$it")
            bindMovie(it)
            binding.progressBar.visibility = View.INVISIBLE
        })
    }

    private fun displaySimilarMovies() {
        similarMoviesRecyclerViewInit()
        viewModel.getSimilarMovies().observe(this, {
            adapter.getSimilarMovieList(it)
            Log.d(TAG, "$it")
        })
    }
}
