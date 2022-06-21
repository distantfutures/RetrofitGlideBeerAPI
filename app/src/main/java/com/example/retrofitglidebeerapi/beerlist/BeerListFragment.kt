package com.example.retrofitglidebeerapi.beerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitglidebeerapi.R
import com.example.retrofitglidebeerapi.databinding.FragmentBeerListBinding

class BeerListFragment : Fragment() {

    private val viewModel: BeerListViewModel by lazy {
        ViewModelProvider(this).get(BeerListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentBeerListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_beer_list, container, false
        )

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel through XML data variable
        binding.fragmentViewModel = viewModel

        // Initialize Adapter
        binding.beerList.adapter = BeerListAdapter()

        return binding.root
    }
}
