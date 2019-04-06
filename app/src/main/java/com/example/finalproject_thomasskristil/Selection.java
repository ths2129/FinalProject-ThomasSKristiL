package com.example.finalproject_thomasskristil;


public class Selection {

    public int activity;
    public int description;
    public int photoID;
    //public int link;

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

/*
   // public int getLink() {
        return link;
  //  }

    public void setLink(int link) {
        this.link = link;
    }

*/



    public Selection (int activity, int description, int photoID){
        this.activity = activity;
        this.description = description;
        this.photoID = photoID;
        // this.link = link;

    }



}

