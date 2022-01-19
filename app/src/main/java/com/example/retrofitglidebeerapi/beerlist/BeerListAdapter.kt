package com.example.retrofitglidebeerapi.beerlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitglidebeerapi.BeerList
import com.example.retrofitglidebeerapi.R
import java.util.*

class BeerListAdapter(private val beerList: List<BeerList>) : RecyclerView.Adapter<BeerListAdapter.ViewHolder>() {
//    var beerData = listOf<BeerList>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun getItemCount(): Int = 20
        // beerData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = beerList[position]
        holder.bind(item)
        Log.i("ResponseAdapter", "pokeList: ${beerList.size}")
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beerName: TextView = itemView.findViewById(R.id.beer_name)
        val beerImage: ImageView = itemView.findViewById(R.id.beer_image)

        fun bind(
            item: BeerList
        ) {
            beerName.text = item.name.capitalizeWords()
        }
        fun String.capitalizeWords(): String = split(" ").joinToString(" ") {
            it.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.beer_list_item, parent, false)
                return ViewHolder(view)
            }
        }
    }
}