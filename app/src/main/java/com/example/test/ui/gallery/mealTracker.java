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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.test.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class mealTracker extends Fragment {
    private MealTrackerViewModel mealTrackerViewModel;
    String breakfast, lunch, dinner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mealTrackerViewModel=
                new ViewModelProvider(this).get(MealTrackerViewModel.class);
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_meal_tracker, container, false);

        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        breakfast =sharedPref.getString("BF_pref", "");
        lunch =sharedPref.getString("LC_pref", "");
        dinner =sharedPref.getString("DN_pref", "");

        TextView text = (TextView) root.findViewById(R.id.textAddLC);
        text.setText(lunch);
        text = (TextView) root.findViewById(R.id.textAddBF);
        text.setText(breakfast);
        text = (TextView) root.findViewById(R.id.textAddDN);
        text.setText(dinner);


        FloatingActionButton backbtn=(FloatingActionButton) root.findViewById(R.id.floatingActionButton4);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new GalleryFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                root.findViewById(R.id.addBF).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.addLC).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.addDN).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textAddBF).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textAddLC).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textAddDN).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.floatingActionButton4).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textAddLC).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textAddDN).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.editLC).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.editBF).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.editDN).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.addBF).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.addDN).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.addLC).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textView1).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textView3).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textView4).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textView6).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.textView5).setVisibility(View.INVISIBLE);
                transaction.replace(R.id.mealFrame, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        final EditText editText1=(EditText)root.findViewById(R.id.editBF);
        ImageButton add1=(ImageButton) root.findViewById(R.id.addBF);
        add1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) root.findViewById(R.id.editBF);
                String temp = editText.getText().toString();

                if (!"".equals(temp)) {
                    breakfast = breakfast + "\n"+ temp;
                }
                final TextView textView1 = root.findViewById(R.id.textAddBF);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("BF_pref", breakfast);
                editor.commit();
                String finalValue = breakfast;
                mealTrackerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged( String n) {
                        textView1.setText(breakfast);
                    }
                });
            }
        });



        final EditText editText2=(EditText)root.findViewById(R.id.editLC);
        ImageButton add2=(ImageButton) root.findViewById(R.id.addLC);
        add2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) root.findViewById(R.id.editLC);
                String temp = editText.getText().toString();

                if (!"".equals(temp)) {
                    lunch = lunch + "\n"+ temp;
                }
                final TextView textView1 = root.findViewById(R.id.textAddLC);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("LC_pref", lunch);
                editor.commit();
                String finalValue = lunch;
                mealTrackerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged( String n) {
                        textView1.setText(lunch);
                    }
                });
            }
        });


        final EditText editText3=(EditText)root.findViewById(R.id.editDN);
        ImageButton add3=(ImageButton) root.findViewById(R.id.addDN);
        add3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) root.findViewById(R.id.editDN);
                String temp = editText.getText().toString();

                if (!"".equals(temp)) {
                    dinner = dinner + "\n"+ temp;
                }
                final TextView textView1 = root.findViewById(R.id.textAddDN);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("DN_pref", dinner);
                editor.commit();
                String finalValue = dinner;
                mealTrackerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged( String n) {
                        textView1.setText(dinner);
                    }
                });
            }
        });


        return root;
    }
}