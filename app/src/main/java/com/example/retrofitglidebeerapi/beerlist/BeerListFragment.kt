package com.example.retrofitglidebeerapi.beerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.retrofitglidebeerapi.BeerList
import com.example.retrofitglidebeerapi.R
import com.example.retrofitglidebeerapi.databinding.FragmentBeerListBinding

class BeerListFragment : Fragment() {

    val dummyBeerList = generateDummyList(20)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentBeerListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_beer_list, container, false
        )

        binding.beerList.adapter = BeerListAdapter(dummyBeerList)
        return binding.root
    }
    fun generateDummyList(size: Int): List<BeerList> {

        val list = ArrayList<BeerList>()

        for (i in 0 until size) {
            val beer = when (i % 3) {
                0 -> "LaGunitas"
                1 -> "Corona"
                else -> "Bud Lite"
            }

            val item = BeerList(beer)
            list += item
        }

        return list
    }
}
