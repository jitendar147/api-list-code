package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    //String request_url= "https://vmatelive.com/vmatelite/restapi/get_user.php";
    String request_url= "https://api.unsplash.com/photos/?client_id=8634366274bd23efb9b023fb9b2c6502e67f7dd5d6a7962b3b49fbee170940f8";
    ListAdapter mAdapter;
    List<ModelList> list;
    RequestQueue rq;
    JsonArrayRequest jsonArrayRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_1);

        recyclerView = findViewById(R.id.contect_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new ListAdapter(this, new ListAdapter.Interactor() {
            @Override
            public void onChatClicked(int position, ModelList contact) {

            }
            @Override
            public void onChatLongClicked(int position, ModelList contact) {

            }
        });
        rq = CustomVolleyRequest.getInstance(this).getRequestQueue();
        list = new ArrayList<>();
        setDataAdaptertoList();

    }

    private void setDataAdaptertoList() {
        rq = CustomVolleyRequest.getInstance(this).getRequestQueue();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               for(int i = 0; i< response.length(); i++) {

                    ModelList mList = new ModelList();
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mList.setId(jsonObject.getString("id"));
                        //get urls
                        JSONObject  getUrls = jsonObject.getJSONObject("urls");
                        mList.setImageView(getUrls.getString("thumb"));
                        mList.setFullSizeImageView(getUrls.getString("full"));
                        //get description
                        mList.setDescription(jsonObject.getString("description"));
                        //get users
                        JSONObject  getUsers = jsonObject.getJSONObject("user");
                        mList.setPersonName(getUsers.getString("name"));
                        mList.setPersonLocation(getUsers.getString("location"));
                        mList.setPersonBio(getUsers.getString("bio"));

                        //get user image
                        String getProfile = getUsers.getString("profile_image");
                        if(!getProfile.isEmpty()){
                            JSONObject  profile = getUsers.getJSONObject("profile_image");
                            mList.setPersonImage(profile.getString("medium"));
                            mList.setPersonImageFullSize(profile.getString("large"));
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    list.add(mList);

                    mAdapter.setImageList(list);
                    recyclerView.setAdapter(mAdapter);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        CustomVolleyRequest.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}