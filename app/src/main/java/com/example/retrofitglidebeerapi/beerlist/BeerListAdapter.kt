package com.example.retrofitglidebeerapi.beerlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitglidebeerapi.BeerList
import com.example.retrofitglidebeerapi.databinding.BeerListItemBinding
import java.util.*

class BeerListAdapter : ListAdapter<BeerList, BeerListAdapter.ViewHolder>(DiffCallback) {
    class ViewHolder(private var binding: BeerListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: BeerList
        ) {
            binding.beers = item
            binding.executePendingBindings()
        }
//        fun String.capitalizeWords(): String = split(" ").joinToString(" ") {
//            it.replaceFirstChar {
//                if (it.isLowerCase()) it.titlecase(
//                    Locale.getDefault()
//                ) else it.toString()
//            }
//        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<BeerList>() {
        override fun areItemsTheSame(oldItem: BeerList, newItem: BeerList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BeerList, newItem: BeerList): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(BeerListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)
        Log.i("ResponseAdapter", "BeerListSize")
    }
}