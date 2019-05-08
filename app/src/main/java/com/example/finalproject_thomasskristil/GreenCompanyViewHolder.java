package com.example.finalproject_thomasskristil;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast; //Library imports



public class GreenCompanyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public CardView cardViews;
    public TextView activityGreen;
    public TextView descriptionGreen;
    public ImageView greenImage; //Global and Public variables can be used throughout classes
    public Context context;
    public RelativeLayout relativeLayout;


    public GreenCompanyViewHolder(final View itemViews, final Context context) { //View is only what is needed
        super(itemViews); //inheritance

        itemViews.setOnClickListener(this); //pass data to onclick
        cardViews = (CardView) itemViews.findViewById(R.id.card_view_green);
        activityGreen = (TextView) itemViews.findViewById(R.id.company);
        descriptionGreen = (TextView) itemViews.findViewById(R.id.description_green);
        greenImage = (ImageView) itemViews.findViewById(R.id.green_image);
        relativeLayout = (RelativeLayout) itemViews.findViewById(R.id.recycler_view_green);
        itemViews.setOnClickListener(this);

        itemViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView descript = (TextView) itemViews.findViewById(R.id.company);
                String message = descript.getText().toString();

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
