package com.example.imagegenerator.favoritos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegenerator.R
import com.example.imagegenerator.databinding.ItemFavoritosBinding

class Favoritos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        //val myDataset = DataFavoritos().loadFavoritos()
       // val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_favoritos)
        //recyclerView.adapter = RecyclerView.Adapter(this, myDataset)
        //recyclerView.setHasFixedSize(true)
    }
}

