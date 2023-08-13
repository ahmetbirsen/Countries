package com.example.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countries.R
import com.example.countries.databinding.FragmentCountryBinding
import com.example.countries.util.downloadFromUrl
import com.example.countries.util.placeholderProgressBar
import com.example.countries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {
    var binding : FragmentCountryBinding?=null
    private lateinit var viewModel : CountryViewModel
    private var countryUuid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid

        }
        viewModel = ViewModelProvider(this).get(CountryViewModel ::class.java)
        viewModel.getDataFromRoom(countryUuid)


        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
            binding!!.countryName.text = country.countryName
            binding!!.countryCapital.text = country.countryCapital
            binding!!.countryCurrency.text = country.countryCapital
            binding!!.countryRegion.text = country.countryRegion
            binding!!.countryLanguage.text = country.countryLanguage
            context?.let {
                binding!!.countryImage.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))
            }
            }

        })
    }

}