package com.example.movies

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso

class MoviesRecyclerAdapter() : RecyclerView.Adapter<MoviesViewHolder>(){

    private val posterPath = "http://image.tmdb.org/t/p/w300/"
    private var list= mutableListOf<Root.Movie>()
    private  var context: Fragment?=null
    constructor(list: MutableList<Root.Movie>,context: Fragment):this(){
        this.list=list
        this.context=context
    }
    constructor(list: MutableList<Root.Movie>):this(){
        this.list=list
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MoviesViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.single_movie,p0,false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MoviesViewHolder, p1: Int) {
        val movie = list[p1]
        p0.title.text = movie.title
        p0.rate.text = movie.vote_average.toString()

         val path = posterPath+movie.poster_path
        Picasso.get().load(path).into(p0.poster)

        p0.itemView.setOnClickListener {
            if (context==null){

                return@setOnClickListener
            }
                context!!.findNavController().navigate(MainPageDirections.actionMainPageToMovieDetail(movie.id))
        }
    }

}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.movie_name)
    val rate : TextView = itemView.findViewById(R.id.movie_rate)
    val poster : ImageView = itemView.findViewById(R.id.movie_img)

}