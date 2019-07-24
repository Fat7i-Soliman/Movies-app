package com.example.movies


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.*
import com.example.movies.popular.PopularMovies
import com.example.movies.rating.TopRating
import com.example.movies.search.SearchFragment


class MainPage : Fragment() {

    private lateinit var moviesPagerAdapter: MoviesPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tab: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager= view.findViewById(R.id.viewPager)
        tab= view.findViewById(R.id.tabLayout)
        moviesPagerAdapter= MoviesPagerAdapter(childFragmentManager)

        viewPager.adapter=moviesPagerAdapter
        tab.setupWithViewPager(viewPager)
    }



class MoviesPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    override fun getItem(p0: Int): Fragment? {
        return when(p0){
            0->  PopularMovies()
            1->  TopRating()
            2-> SearchFragment()
            else ->  null
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->  "Popular Movies"
            1->  "Top Rated"
            2-> "Find a movie"
            else ->  null
        }
    }
}