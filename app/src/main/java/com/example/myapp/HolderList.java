package com.example.myapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HolderList extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView description;
    public ImageView personImage;
    public TextView personName;


    public HolderList(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        description = (TextView) itemView.findViewById(R.id.tv_description);
        personImage = (ImageView) itemView.findViewById(R.id.profile_image);
        personName = (TextView) itemView.findViewById(R.id.tv_person_name);
    }
}