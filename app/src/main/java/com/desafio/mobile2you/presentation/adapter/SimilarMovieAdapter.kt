package com.desafio.mobile2you.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies
import com.desafio.mobile2you.databinding.ItemSimilarMovieBinding

class SimilarMovieAdapter : RecyclerView.Adapter<SimilarMovieAdapter.ViewHolder>() {

    private var similarMovieList = emptyList<SimilarMovie>()

    class ViewHolder(val binding: ItemSimilarMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemSimilarMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            Glide.with(similarMoviePoster)
                .load("https://image.tmdb.org/t/p/original/${similarMovieList[position].posterPath}")
                .into(similarMoviePoster)
            similarMovieTitle.text = similarMovieList[position].title
            similarMovieReleaseYear.text = similarMovieList[position].releaseDate.substring(0, 4)
        }
    }

    override fun getItemCount(): Int = similarMovieList.size

    fun getSimilarMovieList(similarMovies: SimilarMovies) {
        val similarMovieList = similarMovies.similarMovies
        this.similarMovieList = similarMovieList
        notifyDataSetChanged()
    }
}
