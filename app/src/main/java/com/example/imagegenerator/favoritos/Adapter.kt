package com.example.imagegenerator.favoritos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegenerator.R
/*
class Adapter(private val context: Context ,
              private val dataset: List<Favoritos>) : RecyclerView.Adapter <Adapter.ItemViewHolder>(){

    class ItemViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        val imageView : ImageView = view.findViewById(R.id.item_images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favoritos, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.imageView.view = context.resources.getString(item.stringResourceId)
    }

    override fun getItemCount() = dataset.size
    }


}

 */