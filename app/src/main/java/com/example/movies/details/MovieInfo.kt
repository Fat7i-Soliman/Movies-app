package com.example.movies.details

data class MovieInfo(val backdrop_path:String,
                     val genres :List<Genres>,
                     val id:Int ,
                     val imdb_id :String,
                     val overview:String,
                     val poster_path :String,
                     val release_date :String,
                     val runtime:Int ,
                     val title :String,
                     val vote_average:Double ){

                     data class Genres(val name:String)
}