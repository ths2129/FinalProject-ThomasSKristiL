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

import java.util.ArrayList;

public class SelectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //RecycleView is a subclass of ViewGroup that displays child views
    //Recycler view helps store memory by not loading everything

    public CardView cardView;
    public TextView activitySelection;
    public TextView descriptionSelection;
    public ImageView activityImage; //Global and Public variables can be used throughout classes
    public Context context;
    public RelativeLayout relativeLayout;


    public SelectionViewHolder(final View itemView, final Context context) { //View is only what is needed
        super(itemView); //inheritance

        itemView.setOnClickListener(this); //pass data to onclick
        cardView = (CardView) itemView.findViewById(R.id.card_view_selection);
        activitySelection = (TextView) itemView.findViewById(R.id.activity);
        descriptionSelection = (TextView) itemView.findViewById(R.id.description);
        activityImage = (ImageView) itemView.findViewById(R.id.activity_image);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.recycler_view);
        itemView.setOnClickListener(this);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView descript = (TextView) itemView.findViewById(R.id.activity);
                String message = descript.getText().toString();


                Toast.makeText(v.getContext(), "This is " + message + " option ", Toast.LENGTH_LONG).show();

                //go through each item if you have few items within recycler view
                if (getLayoutPosition() == 0) {
                    Intent intent = new Intent(context, CarbonQuizActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                } else if (getLayoutPosition() == 1) {
                    Intent intent = new Intent(context, EmailRepresentativeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                } else if (getLayoutPosition() == 2) {
                    Intent intent = new Intent(context, ForumRules.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                } else if (getLayoutPosition() == 3) {

                } else if (getLayoutPosition() == 4) {
                    Intent intent = new Intent (context, RecycleMapsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                } else if (getLayoutPosition() == 5) {
                    Intent intent = new Intent(context, GameStart.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });



    /*    public interface OnItemClickListener{
        void OnItemClick(int position);*/


        //@Override
        // public void onClick(View v) { //register each cardview
        //    int position = getAdapterPosition();


    }

    @Override
    public void onClick(View v) {

    }
}