package com.example.movies.rating

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

class TopRating : Fragment() {

    private lateinit var viewModel: TopRatingViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: MoviesRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_rating_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView= view.findViewById(R.id.topRating_recycler)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TopRatingViewModel::class.java)

        recyclerView.layoutManager= GridLayoutManager(context,2)

        viewModel.movies.observe(this, Observer {
            if (it!=null){
                adapter= MoviesRecyclerAdapter(it as MutableList<Root.Movie>,this)
                recyclerView.adapter= adapter
            }
        })
    }

}
