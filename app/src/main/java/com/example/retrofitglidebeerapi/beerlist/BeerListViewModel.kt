package com.example.retrofitglidebeerapi.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitglidebeerapi.BeerList
import com.example.retrofitglidebeerapi.network.BeerApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BeerListViewModel : ViewModel() {
    private val TAG = "VMTest"

    private val _beers = MutableLiveData<List<BeerList>>()
    val beers: LiveData<List<BeerList>>
        get() = _beers

    private val disposable = CompositeDisposable()
    init {
        getBeers()
    }

    private fun getBeers() {
        val beersApi = BeerApi.retrofitService.getBeerList()
        disposable.add(beersApi
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _beers.postValue(it)
                },
                {
                    _beers.postValue(null)
                    Log.e(TAG, "onError: $it")
                }
            )
        )
    }
    fun clearDisposable() {
        disposable.clear()
    }

}