package com.desafio.mobile2you.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.desafio.mobile2you.R
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

        recyclerViewInit()

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

    private fun recyclerViewInit() {
        adapter = SimilarMovieAdapter()
        binding.similarMoviesRecyclerView.adapter = adapter
        binding.similarMoviesRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun displayMovie() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getMovie().observe(this, {
            Log.d(TAG, "$it")
            binding.movieTitle.text = it.title
            "${it.voteCount} Likes".also { text -> binding.likeCount.text = text }
            "${it.popularity} Watched".also { text -> binding.popularityCount.text = text }
            binding.movieDescription.text = it.overview
            binding.genreNames.text = "${it.genres.map { genre -> genre.name }}"
            Glide.with(binding.moviePoster)
                .load("https://image.tmdb.org/t/p/original/${it.posterPath}")
                .into(binding.moviePoster)
            binding.progressBar.visibility = View.INVISIBLE
        })
    }

    private fun displaySimilarMovies() {
        viewModel.getSimilarMovies().observe(this, {
            adapter.getSimilarMovieList(it)
            Log.d(TAG, "$it")
        })
    }
}
