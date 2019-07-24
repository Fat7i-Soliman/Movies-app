package com.example.movies.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController

import com.example.movies.R
import com.squareup.picasso.Picasso

class MovieDetail : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var factory: MovieDetailsViewModelFactory
    private lateinit var name:TextView
    private lateinit var genres:TextView
    private lateinit var rate:TextView
    private lateinit var overView:TextView
    private lateinit var time:TextView
    private lateinit var date:TextView
    private lateinit var button: Button
    private val posterPath = "http://image.tmdb.org/t/p/w400/"
    private lateinit var poster:ImageView
    private lateinit var cover:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = MovieDetailArgs.fromBundle(arguments!!)
        factory = MovieDetailsViewModelFactory(args.id)
        viewModel = ViewModelProviders.of(this,factory).get(MovieDetailViewModel::class.java)

        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        name = view.findViewById(R.id.info_name)
        genres=view.findViewById(R.id.info_genres)
        rate=view.findViewById(R.id.info_rate)
        overView= view.findViewById(R.id.info_overView)
        time = view.findViewById(R.id.info_time)
        date = view.findViewById(R.id.info_date)

        button = view.findViewById(R.id.imdb)
        poster= view.findViewById(R.id.info_poster)
        cover=view.findViewById(R.id.info_cover)


    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.details.observe(this, Observer {
            if (it!=null){
                name.text= it.title
                rate.text= it.vote_average.toString()
                overView.text=it.overview
                time.text="${it.runtime} min"
                date.text=it.release_date
                setPics(poster,it.poster_path)
                setPics(cover,it.backdrop_path)
                imdbClick(it.imdb_id)
            }
        })
        viewModel.genders.observe(this, Observer {list->
            if (list != null) {
                var type=""
                for (i in list){
                    type += if (i!=list.last()){
                        "${i.name}, "
                    }else{
                        i.name
                    }
                }
                genres.text= type
                Log.i("MovieDetail","genres-not null")

            }else{
                Log.i("MovieDetail","genres")

            }
        })

    }


    private fun setPics(imageView: ImageView,path:String){
        Picasso.get().load(posterPath+path).into(imageView)
    }

    private fun setTime(time:Int):String{
        var hour =0
        val fixed: String
        while (time >60){
            hour++
            time-60
        }
        fixed = hour.toString()+"h "+time.toString()+"min"
        return fixed
    }

    private fun imdbClick(id:String){
        val link = "https://www.imdb.com/title/$id"

        button.setOnClickListener {
            findNavController().navigate(MovieDetailDirections.actionMovieDetailToWebFragment(link))
        }
    }

}
