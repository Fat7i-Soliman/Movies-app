package com.example.movies.popular

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movies.MoviesRecyclerAdapter
import com.example.movies.R
import com.example.movies.Root

class PopularMovies : Fragment() {

    private lateinit var recyclerView :RecyclerView
    private lateinit var viewModel: PopularMoviesViewModel
    private lateinit var adapter: MoviesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.popular_movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView= view.findViewById(R.id.popular_recycler)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel::class.java)
        recyclerView.layoutManager=GridLayoutManager(context,2)


        viewModel.movies.observe(this, Observer {
            if (it!=null){
                adapter= MoviesRecyclerAdapter(it as MutableList<Root.Movie>,this)
                recyclerView.adapter= adapter
            }
        })



    }


}

