package com.example.imagegenerator.favoritos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegenerator.R
import com.example.imagegenerator.data.RandomImage
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User

class Favoritos : AppCompatActivity() {
    private lateinit var imagensRecyclerView: RecyclerView
    private lateinit var imagensArrayList : ArrayList<RandomImage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
        setContentView(R.layout.activity_favoritos)

        //carregaColectionFireBase()


        imagensRecyclerView = findViewById(R.id.recycler_view_favoritos)
        imagensRecyclerView.layoutManager = LinearLayoutManager ( this)
        imagensRecyclerView.setHasFixedSize(true)

        imagensArrayList = arrayListOf<RandomImage>()
        getImagesData()


    }

    private fun getImagesData() {


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
            imagensArrayList.add(ri)

        }
        imagensRecyclerView.adapter = ItemAdapter(imagensArrayList)
        Log.i("imagens", imagensArrayList.size.toString())
    }

}




