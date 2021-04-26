package com.example.test.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HomeTabFragment extends Fragment {

    private HomeViewModel slideshowViewModel;
    RecyclerView items;
    ArrayList<String> allItems;
    Set<String> defaultString = new HashSet<String>();

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




        Button searchButton = (Button) root.findViewById(R.id.get_recipe_bttn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overLaySelectItems.setVisibility(View.VISIBLE);
                searchButton.setVisibility(View.INVISIBLE);
            }
        });

        return root;
    }
}