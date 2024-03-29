package com.example.movies.search


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.movies.MoviesRecyclerAdapter
import com.example.movies.R
import com.example.movies.Root


class SearchFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: MoviesRecyclerAdapter
    private lateinit var searchView:SearchView
    private lateinit var adapterInterface: MoviesRecyclerAdapter.InfoAdapterInterface
    var t:String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView= view.findViewById(R.id.result_recycler)
        recyclerView.layoutManager= GridLayoutManager(context,2)

        setHasOptionsMenu(true)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)
            Log.i("SearchFragment","oncreate")

        adapterInterface= object : MoviesRecyclerAdapter.InfoAdapterInterface{
            override fun OnItemClicked(item_id: Int) {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToMovieDetail(item_id))
            }

        }


        viewModel.moviesList.observe(this, Observer {list->
            if (list!=null){
                adapter= MoviesRecyclerAdapter(list as MutableList<Root.Movie>,adapterInterface)
                recyclerView.adapter=adapter
            }
        })
        viewModel.text.observe(this, Observer {text->

            if (text!=null){
                if (text != t) {
                    viewModel.getMovies(text)
                    t=text
                }

            }
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu!!.clear()
        inflater!!.inflate(R.menu.search,menu)


          searchView = menu.findItem(R.id.search).actionView as SearchView
          searchView.requestFocus()
        //val searchView = SearchView((context as MainActivity).supportActionBar!!.themedContext)


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.i("SearchFragment",p0)
                viewModel.setText(p0!!)
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchView.isIconified=true
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.i("SearchFragment",p0)

                return false
            }

        })
    }

    override fun onStop() {
        super.onStop()
        searchView.setQuery("",false)
        searchView.isIconified=true
    }
}
