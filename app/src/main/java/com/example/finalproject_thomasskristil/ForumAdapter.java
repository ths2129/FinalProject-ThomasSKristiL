package com.example.finalproject_thomasskristil;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

    public class ForumAdapter extends ArrayAdapter<ForumConstructor> {
        public ForumAdapter(Context context, int resource, List<ForumConstructor> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
            }

            ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
            TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
            TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

            ForumConstructor message = getItem(position);

            boolean isPhoto = message.getPhotoUrl() != null; //either or image or text
            if (isPhoto) { // is it a photo and uses glide from depencies
                messageTextView.setVisibility(View.GONE);
                photoImageView.setVisibility(View.VISIBLE);
                Glide.with(photoImageView.getContext())
                        .load(message.getPhotoUrl())
                        .into(photoImageView);
            } else {
                messageTextView.setVisibility(View.VISIBLE);
                photoImageView.setVisibility(View.GONE);
                messageTextView.setText(message.getText());
            }
            authorTextView.setText(message.getName()); //secure location to store files!

            return convertView;
        }
    }


