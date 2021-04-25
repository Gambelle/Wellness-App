package com.example.test.ui.gallery;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import android.content.SharedPreferences;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Nutrients extends Fragment {
    private NutrientsViewModel nutrientsViewModel;
    int carbs, protien, fats;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        nutrientsViewModel =
                new ViewModelProvider(this).get(NutrientsViewModel.class);
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_nutrients, container, false);
        root.findViewById(R.id.nutrientsFrame).setVisibility(View.VISIBLE);

        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        carbs=sharedPref.getInt("carbs_pref", 0);
        protien=sharedPref.getInt("protien_pref", 0);
        fats=sharedPref.getInt("fats_pref", 0);
        TextView text = (TextView) root.findViewById(R.id.carbsText);
        text.setText(carbs+" g");
        text = (TextView) root.findViewById(R.id.protienText);
        text.setText(protien+" g");
        text = (TextView) root.findViewById(R.id.fatsText);
        text.setText(fats+" g");




        FloatingActionButton backbtn=(FloatingActionButton) root.findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new GalleryFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                root.findViewById(R.id.carbs).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.protiens).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.protienText).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.fats).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.fatsText).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.save1).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.save2).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.save3).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.back).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.carbsText).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.CarbsTitle).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.ProtienTitle).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.FatsTitle).setVisibility(View.INVISIBLE);
                transaction.replace(R.id.nutrientsFrame, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        final EditText editText1=(EditText)root.findViewById(R.id.carbs);
        Button btnS1=(Button)root.findViewById(R.id.save1);
        btnS1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) root.findViewById(R.id.carbs);
                String temp = editText.getText().toString();

                if (!"".equals(temp)) {
                    carbs = Integer.parseInt(temp);
                }
                final TextView textView1 = root.findViewById(R.id.carbsText);
                if(carbs>10000)
                {
                    carbs=9999;
                }
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("carbs_pref", carbs);
                editor.commit();
                int finalValue = carbs;
                nutrientsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged( String n) {
                        textView1.setText(finalValue +" g");
                    }
                });
            }
        });

        final EditText editText2=(EditText)root.findViewById(R.id.protiens);
        Button btnS2=(Button)root.findViewById(R.id.save2);
        btnS2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) root.findViewById(R.id.protiens);
                String temp = editText.getText().toString();
                if (!"".equals(temp)) {
                    protien = Integer.parseInt(temp);
                }
                Log.d("myTag", String.valueOf(protien));
                final TextView textView1 = root.findViewById(R.id.protienText);
                if(protien>10000)
                {
                    protien=9999;
                }
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("protien_pref", protien);
                editor.commit();
                int finalValue = protien;
                nutrientsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged( String n) {
                        textView1.setText(finalValue +" g");
                    }
                });
            }
        });

        final EditText editText3=(EditText)root.findViewById(R.id.fats);
        Button btnS3=(Button)root.findViewById(R.id.save3);
        btnS3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) root.findViewById(R.id.fats);
                String temp = editText.getText().toString();
                if (!"".equals(temp)) {
                    fats = Integer.parseInt(temp);
                }
                Log.d("myTag", String.valueOf(fats));
                final TextView textView1 = root.findViewById(R.id.fatsText);
                if(fats>10000)
                {
                    fats=9999;
                }
                int finalValue = fats;
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("fats_pref", fats);
                editor.commit();
                nutrientsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged( String n) {
                        textView1.setText(finalValue +" g");
                    }
                });

            }
        });
        return root;
    }
}