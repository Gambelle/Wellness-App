package com.example.test.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Settings extends Fragment {
    private SettingsViewModel settingsViewModel;

    int Gcarbs, Gprotien, Gfats, Gcal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        View root= inflater.inflate(R.layout.fragment_settings, container, false);

        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);

        FloatingActionButton backbtn= root.findViewById(R.id.floatingActionButton);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new GalleryFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                root.findViewById(R.id.EditSCals).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.EditSCarbss).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.EditSFats).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.EditSProtiens).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.title).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Sbtn1).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Sbtn2).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Sbtn3).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Sbtn4).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.floatingActionButton).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.SCals).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.SFats).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.SProtiens).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.SCarbs).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.text1).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.text2).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.text3).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.text5).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.text4).setVisibility(View.INVISIBLE);
                transaction.replace(R.id.settingsFrame, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });


        final EditText editText1= root.findViewById(R.id.EditSCals);
        Button btnS1= (Button)root.findViewById(R.id.Sbtn1);
        btnS1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText editText = root.findViewById(R.id.EditSCals);
                String temp = editText.getText().toString();

                if (!"".equals(temp)) {
                    Gcarbs = Integer.parseInt(temp);
                }
                final TextView textView1 = root.findViewById(R.id.SCals);
                if(Gcarbs>10000)
                {
                    Gcarbs=9999;
                }
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("Gcalorie_pref", Gcarbs);
                editor.commit();
                int finalValue = Gcarbs;
                settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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