package com.example.movies.popular

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.movies.MainPageDirections
import com.example.movies.MoviesRecyclerAdapter
import com.example.movies.R
import com.example.movies.Root
import com.example.movies.network.Connection

class PopularMovies : Fragment() {

    private lateinit var recyclerView :RecyclerView
    private lateinit var viewModel: PopularMoviesViewModel
    private lateinit var adapter: MoviesRecyclerAdapter
    private lateinit var spinner:ProgressBar
    private lateinit var adapterInterface: MoviesRecyclerAdapter.InfoAdapterInterface


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.popular_movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView= view.findViewById(R.id.popular_recycler)
        spinner = view.findViewById(R.id.loading_spinner)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.layoutManager = GridLayoutManager(context, 2)

        if (Connection().verifyAvailableNetwork(activity as AppCompatActivity)) {

            viewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel::class.java)

            adapterInterface= object : MoviesRecyclerAdapter.InfoAdapterInterface{
                override fun OnItemClicked(item_id: Int) {
                    findNavController().navigate(MainPageDirections.actionMainPageToMovieDetail(item_id))
                }

            }

            viewModel.movies.observe(this, Observer {
                if (it != null) {
                    spinner.visibility = View.INVISIBLE
                    adapter = MoviesRecyclerAdapter(it as MutableList<Root.Movie>, adapterInterface)
                    recyclerView.adapter = adapter

                }
            })

        }else{
            spinner.visibility = View.INVISIBLE
            Toast.makeText(requireContext(),"check your internet connection!",Toast.LENGTH_SHORT).show()
        }

    }


}

