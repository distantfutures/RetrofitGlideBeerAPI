package com.example.retrofitglidebeerapi.network

import com.example.retrofitglidebeerapi.BeerList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.punkapi.com/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// Implement API service interface that return JSON data as a string (through Scalars)
interface BeerApiService {
    // Gets answers objects
    @GET("beers?page=2&per_page=80")
    fun getBeerList():
        Flowable<List<BeerList>>
}

// Creates API object using Retrofit to implement API Service
object BeerApi {
    val retrofitService: BeerApiService by lazy {
        retrofit.create(BeerApiService::class.java)
    }
}
