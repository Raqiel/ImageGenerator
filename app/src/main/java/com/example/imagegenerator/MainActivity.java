package com.example.imagegenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.imagegenerator.data.Api;
import com.example.imagegenerator.data.RandomImage;
import com.example.imagegenerator.databinding.ActivityMainBinding;
import com.example.imagegenerator.favoritos.Favoritos;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity<onOptionsItemSelected> extends AppCompatActivity {


    private ActivityMainBinding binding;
    private Api imagesApi;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private ImageView btCoracao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btCoracao = findViewById(R.id.coracao);

        //Toolbar para menus
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //metodos
        setupHttpClient();
        setupBottonGenerate();
        setupImagesList();
        //setupButtonFavoriteCoracao();


    }

/*
    //TODO salvar a imagem no banco de dados quando o usuario clicar no coraçao
    private void setupButtonFavoriteCoracao() {
        binding.coracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               recuperarDados();

            }
        });



    }



/*
    private void recuperarDados() {
        RandomImage randomImagem = new RandomImage();
        randomImagem.setImage(btCoracao.ima());
    }



        //firebase

        DatabaseReference imagem = referencia.child("imagem");
        referencia.child("id").child("002").child("imagem").setValue("002");
        randomImagem.setImage();
        randomImagem.setId();
        randomImagem.child("100").setValue(RandomImage);

         */

    //Menu opções
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_xml, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //quando clicar em opções-favoritos abre tela de favoritos
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.favoritos:
                //TODO mostrar a tela de favoritos
                paginaFavoritos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void paginaFavoritos() {
        Intent intent = new Intent(getApplicationContext(), Favoritos.class);
        startActivity(intent);
    }

    //Recupera as imagens do site dog.ceo
    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        imagesApi = retrofit.create(Api.class);
    }

    // Implementa botão para gerar nova imagem
    private void setupBottonGenerate() {
        binding.buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupImagesList();
            }
        });
    }

    //desenha as imagens recuperadas da api na tela
    private void setupImagesList() {
        imagesApi.getRandomImage().enqueue(new Callback<RandomImage>() {
            @Override
            public void onResponse(Call<RandomImage> call, Response<RandomImage> response) {
                if (response.isSuccessful()) {
                    RandomImage imagesR = response.body();
                    Glide.with(MainActivity.this).load(imagesR.getImage()).into(binding.imageGenerated);
                    //Teste de recuperação de dados
                    Log.i("imagemteste", "esta dando certo" + imagesR.getImage());

                } else {
                    showErrorMessage();
                }
            }
            @Override
            public void onFailure(Call<RandomImage> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    private void showErrorMessage() {
        //Caso o telefone nao tenha acesso a internet, uma mensagem de erro será apresentada na tela
        Snackbar.make(binding.buttonGenerate, "Erro ao gerar imagem", Snackbar.LENGTH_LONG).show();
    }
}

