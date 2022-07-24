package com.example.imagegenerator.data;

import com.google.gson.annotations.SerializedName;

public class randomImage {
    @SerializedName("message")
    private String Image;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
