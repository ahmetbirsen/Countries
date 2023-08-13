package com.example.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.countries.R
import com.example.countries.adapter.CountryAdapter
import com.example.countries.model.Country
import com.example.countries.viewmodel.FeedViewModel


class FeedFragment : Fragment() {
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())
    private lateinit var recyclerViewCountry : RecyclerView
    private lateinit var countryListError : TextView
    private lateinit var countryLoading : ProgressBar
    private lateinit var swipeRefresh : SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryListError = view.findViewById(R.id.countryError)
        countryLoading = view.findViewById(R.id.countryLoading)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        recyclerViewCountry = view.findViewById(R.id.recyclerView)
        recyclerViewCountry.layoutManager = LinearLayoutManager(context)
        recyclerViewCountry.adapter = countryAdapter


        swipeRefresh.setOnRefreshListener {
            recyclerViewCountry.visibility = View.GONE
            countryListError.visibility = View.GONE
            countryLoading.visibility = View.VISIBLE
            viewModel.refreshData()
            swipeRefresh.isRefreshing = false
        }
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                recyclerViewCountry.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries as ArrayList<Country>)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it){
                    countryListError.visibility = View.VISIBLE
                    recyclerViewCountry.visibility = View.GONE

                }else{
                    countryListError.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    countryLoading.visibility = View.VISIBLE
                    recyclerViewCountry.visibility = View.GONE
                    countryListError.visibility = View.GONE
                }else{
                    countryLoading.visibility = View.GONE
                }
            }
        })
    }

}




















