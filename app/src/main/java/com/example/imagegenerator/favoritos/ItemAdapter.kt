package com.example.imagegenerator.favoritos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.imagegenerator.R
import com.example.imagegenerator.data.RandomImage


class ItemAdapter(
    private val dataset: List<RandomImage>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val imagens: ImageView = view.findViewById(R.id.item_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ItemAdapter.ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favoritos,parent,false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
        Glide.with(holder.itemView).load(item.image)
            .into(holder.imagens)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}




