package com.example.test.ui.gallery;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test.R;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    // The value will be default as empty string because for
    // the very first time when the app is opened, there is nothing to show

    // We can then use the data
    int num, value,goalVal;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        Context context = getActivity();
        //define your intent
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


        // Set the alarm to start at approximately 00:00 h(24h format).
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        //repeteat alarm every 24hours
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);



        SharedPreferences sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        num=sharedPref.getInt("water_pref", 0);
        value=sharedPref.getInt("calorie_pref", 0);
        goalVal=sharedPref.getInt("Gcalorie_pref", 0);

        TextView text = (TextView) root.findViewById(R.id.numWater);
        text.setText(num+" glasses");
        text = (TextView) root.findViewById(R.id.calfood);
        text.setText(value+"/"+goalVal);


        Button btnN=(Button)root.findViewById(R.id.nutrients);
        btnN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Fragment someFragment = new Nutrients();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                root.findViewById(R.id.set).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.cal).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.calfood).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.nutrients).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.calories).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.mealTracker).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.numWater).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.floatingActionButton1).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Water).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Food).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.floatingActionButton2).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.editsettings).setVisibility(View.INVISIBLE);
                transaction.replace(R.id.goals, someFragment, "nutrientsFrame" ); // give your fragment container id in first parameter
                transaction.commit();

            }
        });
        ImageButton btnES=(ImageButton) root.findViewById(R.id.editsettings);
        btnES.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Fragment someFragment = new Settings();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                root.findViewById(R.id.set).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.cal).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.calfood).setVisibility(View.INVISIBLE);

                root.findViewById(R.id.editsettings).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.nutrients).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.calories).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.mealTracker).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.numWater).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.floatingActionButton1).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Water).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Food).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.floatingActionButton2).setVisibility(View.INVISIBLE);
                transaction.replace(R.id.goals, someFragment, "settingsFrame" ); // give your fragment container id in first parameter
                transaction.commit();

            }
        });
        Button btnmT=(Button)root.findViewById(R.id.mealTracker);
        btnmT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment someFragment = new mealTracker();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                root.findViewById(R.id.set).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.cal).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.calfood).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.nutrients).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.editsettings).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.calories).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.mealTracker).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.numWater).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.floatingActionButton1).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Water).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.Food).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.floatingActionButton2).setVisibility(View.INVISIBLE);
                transaction.replace(R.id.goals, someFragment); // give your fragment container id in first parameter
                transaction.commit();
            }
        });
        FloatingActionButton btnP=(FloatingActionButton) root.findViewById(R.id.floatingActionButton1);
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView textView = root.findViewById(R.id.numWater);
                galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged( String n) {
                        textView.setText(addOne(n)+" glasses");
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("water_pref", num);
                        editor.commit();
                    }
                });
            }
        });
        FloatingActionButton btnM=(FloatingActionButton) root.findViewById(R.id.floatingActionButton2);
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView textView = root.findViewById(R.id.numWater);
                galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged( String n) {
                        textView.setText(subOne(n)+" glasses");
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("water_pref", num);
                        editor.commit();
                    }
                });
            }
        });

        final EditText editText=(EditText)root.findViewById(R.id.calories);
        Button btnS=(Button)root.findViewById(R.id.set);
        btnS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) root.findViewById(R.id.calories);
                String temp = editText.getText().toString();
                if (!"".equals(temp)) {
                    value = Integer.parseInt(temp);
                }
                Log.d("myTag", String.valueOf(value));
                final TextView textView = root.findViewById(R.id.calfood);
                if(value>10000)
                {
                    value=9999;
                }
                int finalValue = value;
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("calorie_pref", value);
                editor.commit();
                galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged( String n) {
                        textView.setText(finalValue +"/"+goalVal);
                    }
                });

            }
        });



        return root;
    }

    private String addOne(String n) {
        num=num+1;
        n=String.valueOf(num);
        return (n);
    }
    private String subOne(String n) {
        if(num>0)
        {
            num=num-1;
        }
        n=String.valueOf(num);
        return (n);
    }
}


