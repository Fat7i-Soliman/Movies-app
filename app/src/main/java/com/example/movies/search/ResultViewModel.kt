package com.example.movies.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.movies.Root
import com.example.movies.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultViewModel : ViewModel() {

    private val api_key = "14c57781e6109693571817381184b891"

    private var _response = MutableLiveData<Root>()
    val response : LiveData<Root>
        get() = _response

    private var _moviesList = MutableLiveData<List<Root.Movie>>()
    val moviesList : LiveData<List<Root.Movie>>
        get() = _moviesList

    private var _text = MutableLiveData<String>()
    val text : LiveData<String>
        get() = _text



    fun setText(txt:String){
        _text.value=txt
    }
    fun getMovies(query :String) {

        MovieApi.retrofitService.getSearch(query,api_key).enqueue(object : Callback<Root> {
            override fun onFailure(call: Call<Root>, t: Throwable) {

            }

            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                _response.value=response.body()
                val root:Root = _response.value!!
                _moviesList.value = root.results
            }

        })
    }


}
