package com.example.retrofitglidebeerapi.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitglidebeerapi.BeerList
import com.example.retrofitglidebeerapi.network.BeerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BeerListViewModel : ViewModel() {

    // Create Job
    private val viewModelJob = Job()
    // Create CoroutineScope
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _beers = MutableLiveData<List<BeerList>>()
    val beers: LiveData<List<BeerList>>
        get() = _beers

    init {
        getTheBeers()
    }

    private fun getTheBeers() {
        coroutineScope.launch {
            val response = BeerApi.retrofitService.getBeerList()

            if (response.isSuccessful) {
                Log.i("Response", "Success!")
                val theBeers = response.body()
                Log.i("ResponseListResults", "Beer List: ${theBeers?.size}")
                _beers.value = theBeers

                for (i in 0 until theBeers?.size!!) {
                    Log.i("Response", "Beer: ${theBeers.get(i).image_url}")
                }
            } else {
                Log.i("Response", "Failed")
            }
        }
    }
}