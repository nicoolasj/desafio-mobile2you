package com.desafio.mobile2you.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.desafio.mobile2you.R
import com.desafio.mobile2you.data.api.TMDBService
import com.desafio.mobile2you.data.repository.MovieRepositoryImpl
import com.desafio.mobile2you.data.repository.datasourceimpl.MovieRemoteDataSourceImpl
import com.desafio.mobile2you.databinding.ActivityMainBinding
import com.desafio.mobile2you.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.getMovie().observe(this, {
            Log.d(TAG, "$it")
            binding.textView.text = it.title
            "${it.voteCount} Likes".also { text -> binding.textView2.text = text }
            "${it.popularity} Watched".also { text -> binding.textView3.text = text }
            binding.textView4.text = it.overview
            binding.textView5.text = "${it.genres.map { genre -> genre.name }}"
            Glide.with(binding.imageView)
                .load("https://image.tmdb.org/t/p/original/${it.posterPath}")
                .into(binding.imageView)
        })
        viewModel.getSimilarMovies().observe(this, {
            Log.d(TAG, "$it")
        })
    }
}