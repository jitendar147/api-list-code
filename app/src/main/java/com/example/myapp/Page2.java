package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Page2 extends AppCompatActivity {

    Intent data;
    String ImageUrl, ImageDescription;
    ImageView btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_2);

        data = getIntent();
        ImageUrl = data.getStringExtra("imageUrl");
        ImageDescription = data.getStringExtra("desc");

        TextView tvDescription = findViewById(R.id.tv_description);
        ImageView displayImage = findViewById(R.id.imageViewFullSize);
        btnBack = findViewById(R.id.img_profile_back);

        tvDescription.setText(ImageDescription);
        Glide.with(this)
                .load(ImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.centerCrop()
                .placeholder(R.drawable.img_icon)
                .into(displayImage);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
