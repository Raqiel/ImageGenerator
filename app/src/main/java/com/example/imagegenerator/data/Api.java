package com.example.imagegenerator.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("/breeds/image/random")
    Call<List<randomImage>> getRandomImages();
}
