package com.example.movies.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MovieDetailsViewModelFactory(private val id:Int) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)){
            return MovieDetailViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}