package com.example.retrofitglidebeerapi.network

import com.example.retrofitglidebeerapi.BeerList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.punkapi.com/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(GsonConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

// Implement API service interface that return JSON data as a string (through Scalars)
interface BeerApiService {
    // Gets answers objects
    @GET("beers")
    suspend fun getBeerList():
        Response<List<BeerList>>
}

// Creates API object using Retrofit to implement API Service
object BeerApi {
    val retrofitService: BeerApiService by lazy {
        retrofit.create(BeerApiService::class.java)
    }
}
