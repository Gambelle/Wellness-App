package com.example.test.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PantryFragment extends Fragment {
    String s1[], s2[];
    RecyclerView recyclerView;
    Set<String> defaultString;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fridge, container, false);

        ConstraintLayout overlayItem = (ConstraintLayout) root.findViewById(R.id.additemoverlay);
        overlayItem.setVisibility(View.INVISIBLE);

        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        s1 = sharedPref.getStringSet(getString(R.string.pantryItems), defaultString).toArray(new String[0]);
        s2 = sharedPref.getStringSet(getString(R.string.pantryQuantities), defaultString).toArray(new String[0]);

        recyclerView = root.findViewById(R.id.recyclerView);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), s1,s2);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ImageButton addItem = (ImageButton) root.findViewById(R.id.addItemButton);
        addItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                overlayItem.setVisibility(View.VISIBLE);

            }
        });

        ImageButton addItem2 = (ImageButton) root.findViewById(R.id.addItemButton2);
        addItem2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                overlayItem.setVisibility(View.INVISIBLE);

                // Do something in response to button click
                //Context context = getActivity();
                //SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);



                //SharedPreferences.Editor editor = sharedPref.edit();
                //editor.putStringSet(getString(R.string.saved_high_score_key), newHighScore);
                //editor.apply();

            }
        });

        return root;
    }
}
