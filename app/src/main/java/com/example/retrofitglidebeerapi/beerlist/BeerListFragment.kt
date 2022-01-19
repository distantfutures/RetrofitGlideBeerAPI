package com.example.retrofitglidebeerapi.beerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.retrofitglidebeerapi.R
import com.example.retrofitglidebeerapi.databinding.FragmentBeerListBinding

class BeerListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentBeerListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_beer_list, container, false
        )

        return binding.root
    }
}
