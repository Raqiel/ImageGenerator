package com.example.imagegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.imagegenerator.data.Api;
import com.example.imagegenerator.data.randomImage;
import com.example.imagegenerator.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Api imagesApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setupImagesList();
        setupBottonGenerate();
        setupHttpClient();

        int numero = new Random().nextInt(15);
    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breeds/image/random")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        imagesApi = retrofit.create(Api.class);
    }

    private void setupBottonGenerate() {
        // implementar botão para gerar imagens aleatorias
    }

    private void setupImagesList() {
        //mostrar as imagens consumindo a api
        imagesApi.getRandomImages().enqueue(new Callback<List<randomImage>>() {
            @Override
            public void onResponse(Call<List<randomImage>> call, Response<List<randomImage>> response) {
                if (response.isSuccessful()){
                    List<randomImage> imagesR = response.body();

                    //TODO apagar essa linha de baixo
                    Log.i("imagemteste", "esta dando certo" + imagesR.size());
                }else{
                    showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<List<randomImage>> call, Throwable t) {
                showErrorMessage();

            }
        });
    }

    private void showErrorMessage() {
        Snackbar.make(binding.buttonGenerate, "Erro ao gerar imagem", Snackbar.LENGTH_LONG).show();
    }
}