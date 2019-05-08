package com.example.finalproject_thomasskristil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

public class GreenCompanyAdapter extends RecyclerView.Adapter<GreenCompanyViewHolder> {

    //questions adapted to the View in XML
    public List<GreenCompanyConstructor> companies;
    public Context context;
    public RelativeLayout relativeLayout;



    //private ArrayList<String> mLink = new ArrayList<>();


    public GreenCompanyAdapter(List<GreenCompanyConstructor> companies, Context context) { //constructor
        this.companies = companies;
        this.context = context; //Array and context
        //mLink = link;
    }



    @Override
    public GreenCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_green, viewGroup, false); //takes style but doesn't add it False
        return new GreenCompanyViewHolder(view, context);
    }
    @Override
    public void onBindViewHolder(GreenCompanyViewHolder holder, int viewTypes) {
        GreenCompanyConstructor green = companies.get(viewTypes);
        holder.activityGreen.setText(green.name);
        holder.descriptionGreen.setText(green.text);
        holder.greenImage.setImageResource(green.photoUrl);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
