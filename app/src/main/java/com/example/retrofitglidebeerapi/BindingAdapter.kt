package com.example.retrofitglidebeerapi

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitglidebeerapi.beerlist.BeerListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recycler: RecyclerView, data: List<BeerList>?) {
    val adapter = recycler.adapter as BeerListAdapter
    adapter.submitList(data)
}
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}
