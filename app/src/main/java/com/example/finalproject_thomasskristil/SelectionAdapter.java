package com.example.finalproject_thomasskristil;

import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SelectionAdapter extends RecyclerView.Adapter<SelectionViewHolder> {

    //questions adapted to the View in XML
    public List<Selection> selections; //links to Selection class constructor - that is what binds holders the getters
    public Context context;
    private OnClickRecycleListener mOnClickRecylerViewListener;
    //private ArrayList<String> mLink = new ArrayList<>();



    public SelectionAdapter(List<Selection> selections, Context context) { //constructor
        this.selections = selections;
        this.context = context; //Array and context
        //mLink = link;
    }


    @Override
    public SelectionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) { //this is a parent view being reference // ViewGroup is the parent
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_selection, viewGroup, false); //takes style but doesn't add it False
        return new SelectionViewHolder(view, context); //returns the constructos inflated view
    }

    @Override
    public void onBindViewHolder(SelectionViewHolder holder, int viewType) { //binds values to cardView - linked to view holder
        Selection selection = selections.get(viewType);
        holder.activitySelection.setText(selection.activity);
        holder.descriptionSelection.setText(selection.description);
        holder.activityImage.setImageResource(selection.photoID);

        Intent intent = new Intent(context, MainActivity.class);

    }

    @Override
    public int getItemCount() {
        return selections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnClickRecycleListener onClickRecycleListener;

        public ViewHolder(@NonNull View itemView, OnClickRecycleListener onClickRecycleListener) {
            super(itemView);
            this.onClickRecycleListener = onClickRecycleListener;

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onClickRecycleListener.onNoteClick(getAdapterPosition());//pass position when clicked
        }

    }

    public interface OnClickRecycleListener{
        void onNoteClick(int position);


    }

}

