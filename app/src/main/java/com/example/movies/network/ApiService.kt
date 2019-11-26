package com.example.movies.network

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import com.example.movies.MainActivity
import com.example.movies.Root
import com.example.movies.details.MovieInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val url = "https://api.themoviedb.org/3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
           .baseUrl(url)
           .addConverterFactory(MoshiConverterFactory.create(moshi))
           .build()

interface ApiService{

    @GET("discover/movie")
    fun getObjects(@Query("api_key") key:String,@Query("language") lang :String,@Query("sort")sort :String):
            Call<Root>


    @GET("movie/top_rated")
    fun getObjects(@Query("api_key") key:String,@Query("language") lang :String):
            Call<Root>


    @GET("movie/{id}")
    fun getDetails(@Path("id") id:Int , @Query("api_key") key:String): Call<MovieInfo>

    @GET("search/movie")
    fun getSearch(@Query("query") txt:String, @Query("api_key") key:String): Call<Root>
}

object MovieApi{
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

class Connection{
    fun verifyAvailableNetwork(activity: AppCompatActivity):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}