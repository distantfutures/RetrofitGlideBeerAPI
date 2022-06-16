package com.example.retrofitglidebeerapi.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitglidebeerapi.BeerList
import com.example.retrofitglidebeerapi.network.BeerApi
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.just
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class BeerListViewModel : ViewModel() {
    private val TAG = "VMTest"

    // Create Job
    private val viewModelJob = Job()
    // Create CoroutineScope
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _beers = MutableLiveData<List<BeerList>>()
    val beers: LiveData<List<BeerList>>
        get() = _beers

    val mList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val aList = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    init {
        getTheBeers()
        justTest()
        fromTest()
        fromTestIter()
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

    private fun justTest() {
        val observeable = Observable.just(mList)

        val observer = object : Observer<List<Int>> {
            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe")
            }

            override fun onNext(t: List<Int>) {
                Log.i(TAG, "onNext: ${t}")
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError")
            }

            override fun onComplete() {
                Log.i(TAG, "onComplete")
            }
        }
        observeable.subscribe(observer)
    }
    private fun fromTest() {
        val observeable = Observable.fromArray(aList)

        val observer = object : Observer<Array<Int>> {
            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe")
            }

            override fun onNext(t: Array<Int>) {
                Log.i(TAG, "onNext: ${Arrays.toString(t)}")
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError")
            }

            override fun onComplete() {
                Log.i(TAG, "onComplete")
            }
        }
        observeable.subscribe(observer)
    }
    private fun fromTestIter() {
        val observeable = Observable.fromIterable(mList)

        val observer = object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe")
            }

            override fun onNext(t: Int) {
                Log.i(TAG, "onNext: $t")
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError")
            }

            override fun onComplete() {
                Log.i(TAG, "onComplete")
            }
        }
        observeable.subscribe(observer)
    }
}