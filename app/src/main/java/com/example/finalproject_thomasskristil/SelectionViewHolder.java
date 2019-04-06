package com.example.finalproject_thomasskristil;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast; //Library imports

import java.util.ArrayList;

public class SelectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{ //RecycleView is a subclass of ViewGroup that displays child views
    //Recycler view helps store memory by not loading everything

    public CardView cardView;
    public TextView activitySelection;
    public TextView descriptionSelection;
    public TextView questionAnswer;
    public TextView linkToSite;
    public ImageView activityImage; //Global and Public variables can be used throughout classes
    public Context context;


    public SelectionViewHolder(View itemView, Context context) { //View is only what is needed
        super(itemView); //inheritance

        itemView.setOnClickListener(this); //pass data to onclick
        cardView = (CardView) itemView.findViewById(R.id.card_view_selection);
        activitySelection = (TextView) itemView.findViewById(R.id.activity);
        descriptionSelection = (TextView) itemView.findViewById(R.id.description);
        activityImage = (ImageView) itemView.findViewById(R.id.activity_image);


    }

    @Override
    public void onClick(View v) { //register each cardview
        int position = getAdapterPosition();

    }
}