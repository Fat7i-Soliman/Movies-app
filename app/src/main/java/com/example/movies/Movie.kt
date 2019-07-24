package com.example.movies

data class Root(val page:Int ,val results:List<Movie>){

    data class Movie(val id:Int,
                     val title:String,
                     val vote_average:Double,
                     val poster_path:String)

}
