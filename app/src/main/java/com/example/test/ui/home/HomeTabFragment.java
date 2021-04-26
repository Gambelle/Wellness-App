package com.example.test.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.ui.slideshow.SlideshowViewModel;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HomeTabFragment extends Fragment {

    private HomeViewModel slideshowViewModel;
    RecyclerView items;
    ArrayList<String> allItems;
    Set<String> defaultString = new HashSet<String>();
    ArrayList<String> itemsChecked;
    URL url;
    String rawData;
    HttpURLConnection urlConnection;
    JSONObject reader;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ConstraintLayout overLaySelectItems = (ConstraintLayout) root.findViewById(R.id.search_overlay);
        overLaySelectItems.setVisibility(View.INVISIBLE);
        Context c = getActivity();
        SharedPreferences sp = c.getSharedPreferences("ITEM_STORAGE",c.MODE_PRIVATE);

        items = root.findViewById(R.id.currentItems);

        allItems = new ArrayList<String>(sp.getStringSet("FRIDGE_ITEM_NAMES",defaultString));
        allItems.addAll(sp.getStringSet("PANTRY_ITEM_NAMES",defaultString));

        RecyclerAdapter2 recyclerA = new RecyclerAdapter2(getActivity(),allItems);
        items.setAdapter(recyclerA);
        items.setLayoutManager(new LinearLayoutManager(getActivity()));

        itemsChecked = new ArrayList<String>();


        Button searchButton = (Button) root.findViewById(R.id.get_recipe_bttn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overLaySelectItems.setVisibility(View.VISIBLE);
                searchButton.setVisibility(View.INVISIBLE);
            }
        });

        recyclerA.setWhenClickListener(new RecyclerAdapter2.OnItemsClickListener() {
            @Override
            public void onCheck(String itemName, boolean isCheck) {
                if (isCheck) {
                    itemsChecked.add(itemName);
                    Log.d("Home Tab Fragment","Array after Add: "+itemsChecked.toString());
                }
                else if(!isCheck) {
                    itemsChecked.remove(itemName);
                    Log.d("Home Tab Fragment","Array after Removal: "+itemsChecked.toString());
                }
                else{
                    Log.d("Home Tab Fragment", "error in isCheck");
                }

            }
        });


        Button sendQ = (Button) root.findViewById(R.id.sendQ);
        sendQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuery();
            }
        });
        return root;
    }

    private void createQuery(){
        int l = itemsChecked.size();

        String url = "";
        String website = "http://api.edamam.com/search?";
        String appKey = "&app_key=143092dfd2c7edb60d17f6ba0d27251b";
        String appId = "&app_id=56ecd714";
        String query = "q=";
        for(int i = 0;i < l;i+=1){
            query += itemsChecked.get(i);
        }
        url = website + query + appId + appKey;
        Log.d("Home Tab Fragment","URL sent: "+ url);
        String raw = sendRequest(url);
        try{
            reader = new JSONObject(raw);
            Log.d("Home Tab Fragment","URL Recieved: "+
                    reader.getJSONObject("hits").getJSONObject("0").getJSONObject("recipe").get("url"));
        }
        catch (org.json.JSONException e){
            Log.d("Home Tab Fragment","Invalid JSON");
        }
    }

    private String sendRequest(String r){
        try{
            url = new URL(r);
        }
        catch (java.net.MalformedURLException e){
            return("Empty");
        }
        finally {
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                rawData = readStream(in);
            }
            catch (IOException e){
                return("Error");
            }
            finally {
                urlConnection.disconnect();
                return(rawData);
            }
        }

    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }



}