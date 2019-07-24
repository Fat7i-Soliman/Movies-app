package com.example.movies.popular

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.movies.Root
import com.example.movies.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesViewModel : ViewModel() {

    private val api_key = "14c57781e6109693571817381184b891"
    private val language = "en-US"
    private val sort = "popularity.desc"

    private var _response = MutableLiveData<Root>()
    val response : LiveData<Root>
    get() = _response


    private var _movies = MutableLiveData<List<Root.Movie>>()
    val movies : LiveData<List<Root.Movie>>
        get() = _movies
    init {


        getMovies()

    }

    private fun getMovies() {


        MovieApi.retrofitService.getObjects(api_key,language,sort).enqueue(object :Callback<Root>{
            override fun onFailure(call: Call<Root>, t: Throwable) {

            }

            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                Log.i("PopularMoviesViewModel",response.body().toString())
                _response.value=response.body()
                val root:Root = _response.value!!
                _movies.value = root.results
            }

        })
    }
}

