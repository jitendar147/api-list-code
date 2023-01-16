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

public class Page3 extends AppCompatActivity {

    Intent data;
    String personImageUrl, personProfileName, personLocation, personBio;
    ImageView personProfileImage, btnBack;
    TextView personName, location, bio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_3);

        data = getIntent();
        personImageUrl = data.getStringExtra("personImage");
        personProfileName = data.getStringExtra("personName");
        personLocation = data.getStringExtra("location");
        personBio = data.getStringExtra("bio");

        personProfileImage = findViewById(R.id.person_profile_image);
        personName = findViewById(R.id.tv_profile_person_name);
        location = findViewById(R.id.tv_profile_person_location);
        bio = findViewById(R.id.tv_profile_person_bio);
        btnBack = findViewById(R.id.img_profile_back);

        personName.setText(personProfileName);
        location.setText(personLocation);
        bio.setText(personBio);
        Glide.with(this)
                .load(personImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(personProfileImage);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
