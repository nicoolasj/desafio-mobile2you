package com.desafio.mobile2you.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafio.mobile2you.R
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies

class SimilarMovieAdapter : RecyclerView.Adapter<SimilarMovieAdapter.ViewHolder>() {

    private var similarMovieList = emptyList<SimilarMovie>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView4)
        val textView: TextView = view.findViewById(R.id.textView8)
        val textView2: TextView = view.findViewById(R.id.textView9)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_similar_movie, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            Glide.with(imageView)
                .load("https://image.tmdb.org/t/p/original/${similarMovieList[position].posterPath}")
                .into(imageView)
            textView.text = similarMovieList[position].title
            textView2.text = similarMovieList[position].releaseDate.substring(0, 4)
        }
    }

    override fun getItemCount(): Int = similarMovieList.size

    fun getSimilarMovieList(similarMovies: SimilarMovies) {
        val similarMovieList = similarMovies.similarMovies
        this.similarMovieList = similarMovieList
        notifyDataSetChanged()
    }
}
