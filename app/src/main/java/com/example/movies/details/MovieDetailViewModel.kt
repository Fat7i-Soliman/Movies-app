package com.example.movies.details

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.movies.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(val id:Int) : ViewModel() {

    private val api_key = "14c57781e6109693571817381184b891"

    private var _details = MutableLiveData<MovieInfo>()
    val details : LiveData<MovieInfo>
        get() = _details


    private var _genders = MutableLiveData<List<MovieInfo.Genres>>()
    val genders : LiveData<List<MovieInfo.Genres>>
        get() = _genders
    init {

        Log.i("MovieDetail",id.toString())
        getDetails()
    }

    private fun getDetails() {

        MovieApi.retrofitService.getDetails(id,api_key).enqueue(object :Callback<MovieInfo>{
            override fun onFailure(call: Call<MovieInfo>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                Log.i("MovieDetail",response.isSuccessful.toString())
                if (response.isSuccessful){
                    Log.i("MovieDetail",response.message())
                    Log.i("MovieDetail",response.body().toString())
                    Log.i("MovieDetail",response.errorBody().toString())
                    _details.value = response.body()
                    val info:MovieInfo = details.value!!
                    _genders.value=info.genres

                    Log.i("MovieDetail",_details.value.toString())

                }



            }

        })
    }

}
