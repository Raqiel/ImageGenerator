package com.example.imagegenerator.data;

import com.google.gson.annotations.SerializedName;

public class RandomImage {
    //Classe com o objeto "imagens" a ser recuperado do site

    @SerializedName("message")
    private String Image;

    private int id;
    private boolean favorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {Image = image;
    }
}
