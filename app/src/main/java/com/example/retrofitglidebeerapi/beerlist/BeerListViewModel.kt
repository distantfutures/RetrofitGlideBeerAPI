package com.example.retrofitglidebeerapi.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitglidebeerapi.BeerList
import com.example.retrofitglidebeerapi.network.BeerApi
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BeerListViewModel : ViewModel() {
    private val TAG = "VMTest"

    private val _beers = MutableLiveData<List<BeerList>>()
    val beers: LiveData<List<BeerList>>
        get() = _beers

    init {
        getBeers()
    }

    private fun getBeers() {
        val beersApi = BeerApi.retrofitService.getBeerList()
        beersApi.toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(setBeersObserver())
    }

    private fun setBeersObserver(): Observer<List<BeerList>> {
        return object : Observer<List<BeerList>> {
            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe")
            }
            override fun onNext(t: List<BeerList>) {
                Log.i(TAG, "$t")
                _beers.postValue(t)
            }
            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: $e")
                _beers.postValue(null)
            }
            override fun onComplete() {
                Log.i(TAG, "onComplete")
            }
        }
    }
}