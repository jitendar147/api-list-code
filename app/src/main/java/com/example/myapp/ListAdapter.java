package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<HolderList> {
    private Context context;
    List<ModelList> list = new ArrayList<>();
    Interactor interactor;

    public ListAdapter(Context context, ListAdapter.Interactor interactor) {
        this.interactor = interactor;
        this.context = context;
    }
    public interface Interactor{
        void onChatClicked(int position, ModelList contact);
        void onChatLongClicked(int position, ModelList contact);
    }

    @NonNull
    @Override
    public HolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_photos,parent,false);
        return new HolderList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderList holder, @SuppressLint("RecyclerView") int position) {
        final ModelList data = list.get(position);
        String id = data.getId();
        holder.description.setText(data.getDescription());
        holder.personName.setText(data.getPersonName());
        Glide.with(context)
                .load(data.getImageView())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.img_icon)
                .into(holder.imageView);
        Glide.with(context)
                .load(data.getPersonImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                //.placeholder(R.drawable.img_heart_beat_plc)
                .into(holder.personImage);

       holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactor.onChatClicked(position, data);
                String imageUrl = data.getFullSizeImageView();
                String desc = data.getDescription();
                Intent intent = new Intent(view.getContext(), Page2.class);
                intent.putExtra("imageUrl",imageUrl);
                intent.putExtra("desc", desc);
                view.getContext().startActivity(intent);
            }
        });
        holder.personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactor.onChatClicked(position, data);
                String personImage = data.getPersonImageFullSize();
                String personName = data.getPersonName();
                String location = data.getPersonLocation();
                String person_bio = data.getPersonBio();
                Intent intent = new Intent(view.getContext(), Page3.class);
                intent.putExtra("personImage",personImage);
                intent.putExtra("personName", personName);
                intent.putExtra("location", location);
                intent.putExtra("bio", person_bio);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setImageList(List<ModelList> list){
        this.list = list;
    }
}
