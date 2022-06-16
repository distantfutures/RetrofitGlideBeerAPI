package com.example.retrofitglidebeerapi.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitglidebeerapi.BeerList
import com.example.retrofitglidebeerapi.network.BeerApi
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.just
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class BeerListViewModel : ViewModel() {
    private val TAG = "VMTest"

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
        val beers = BeerApi.retrofitService.getBeerList()

        val observer = object : Observer<List<BeerList>> {
            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe")
            }

            override fun onNext(t: List<BeerList>) {
                Log.i(TAG, "${t}")
                _beers.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError")
            }

            override fun onComplete() {
                Log.i(TAG, "onComplete")
            }
        }

        beers.toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(observer)
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