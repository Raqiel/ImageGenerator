package com.example.imagegenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.imagegenerator.data.Api;
import com.example.imagegenerator.data.RandomImage;
import com.example.imagegenerator.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity<onOptionsItemSelected> extends AppCompatActivity {


    private ActivityMainBinding binding;
    private Api imagesApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Toolbar para menus
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setupHttpClient();
        setupBottonGenerate();
        setupImagesList();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main_xml, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favoritos:
                //TODO mostrar a tela de favoritos
                break;
            case R.id.menu_save:
                //TODO salvar a imagem da tela em uma room
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupHttpClient() {

        //Recupera as imagens do site dog.ceo
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        imagesApi = retrofit.create(Api.class);
    }

    // Implementa botão para gerar nova imagem
    private void setupBottonGenerate() {

        //Evento de clique
        binding.buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupImagesList();
            }
        });
    }


    private void setupImagesList() {

        //Recuperar imagens de forma aleátoria
        imagesApi.getRandomImage().enqueue(new Callback<RandomImage>() {
            @Override
            public void onResponse(Call<RandomImage> call, Response<RandomImage> response) {
                if (response.isSuccessful()) {
                    RandomImage imagesR = response.body();

                    //Desenha na tela as imagens a partir
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

