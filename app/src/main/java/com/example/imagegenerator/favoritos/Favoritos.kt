package com.example.imagegenerator.favoritos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.imagegenerator.R
import com.example.imagegenerator.data.RandomImage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class Favoritos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        carregaColectionFireBase()




        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        //val myDataset = DataFavoritos().loadFavoritos()
       // val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_favoritos)
        //recyclerView.adapter = RecyclerView.Adapter(this, myDataset)
        //recyclerView.setHasFixedSize(true)
    }

    val imagensFavoritas = ArrayList<RandomImage> ()

    fun carregaColectionFireBase() {

        FirebaseFirestore.getInstance().collection("imagens").get().addOnSuccessListener{
            imagensCollection ->
            trataImagem(imagensCollection)
        }

    }

    private fun trataImagem(imagensCollection: QuerySnapshot) {
        for (imagem in imagensCollection) {

            val ri = RandomImage()
            ri.image = imagem.data.get("url") as String?
            ri.id = imagem.id
            imagensFavoritas.add(ri)

        }
        Log.i("imagens", imagensFavoritas.size.toString())
    }

}

