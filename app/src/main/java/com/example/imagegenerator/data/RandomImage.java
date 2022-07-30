package com.example.imagegenerator.data;

import com.google.gson.annotations.SerializedName;

public class RandomImage {
    //Classe com o objeto "imagens" a ser recuperado do site

    @SerializedName("message")
    private String Image;
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getImage() {
        return Image;
    }

    public void setImage(String image) {Image = image;
    }
}
