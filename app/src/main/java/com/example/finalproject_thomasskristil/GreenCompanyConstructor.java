package com.example.finalproject_thomasskristil;

public class GreenCompanyConstructor {
    public int name;
    public int photoUrl;
    public int text;

    public GreenCompanyConstructor(){
        //empty constructor
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(int photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }



    public GreenCompanyConstructor(int name, int photoUrl, int text) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.text = text;
    }

}