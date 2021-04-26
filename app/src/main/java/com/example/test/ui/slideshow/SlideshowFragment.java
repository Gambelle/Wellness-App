package com.example.test.ui.slideshow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.ui.gallery.GalleryFragment;

public class SlideshowFragment extends Fragment {
    int waterNum;
    int calNum, goalCal;
    int carbNum;
    int carbL, carbU;
    int fatsNum;
    int fatsU, fatsL;
    int protienNum;
    int protienU, protienL;

    boolean isWater;
    String achievment="";

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        waterNum=sharedPref.getInt("water_pref", 0);
        calNum=sharedPref.getInt("calorie_pref", 0);
        goalCal=sharedPref.getInt("Gcalorie_pref", 0);
        carbNum=sharedPref.getInt("carbs_pref", 0);
        fatsNum=sharedPref.getInt("fats_pref", 0);
        protienNum=sharedPref.getInt("protien_pref", 0);
        carbL= (int) (calNum*0.1125);
        carbU= (int) (calNum*0.1625);
        fatsL= (int) (calNum*0.02);
        fatsU= (int) (calNum*0.035);
        protienL= (int) (calNum*0.02);
        protienU= (int) (calNum*0.03);
        Log.d("When water goal is met prints achievement to fragment", String.valueOf(waterNum));



        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (waterNum>=10)
                {
                    achievment=achievment+ "\nHydrated!\nReached Water Goal\n\n";

                }
                if (calNum>1000 && calNum<goalCal)
                {
                    achievment=achievment+ "\nHealthy Calorie Intake!\nReached Calorie Goal\n";
                }
                if (carbNum>carbL && carbNum<carbU)
                {
                    achievment=achievment+ "\nCarb! Carbs! Carbs!\nReached Carbs Intake  Goal\n";
                }
                if (protienNum>protienL && protienNum<protienU)
                {
                    achievment=achievment+ "\nProtiens! Protiens! Protiens!\nReached Protien Intake  Goal\n";
                }
                if (fatsNum>fatsL && fatsNum<fatsU)
                {
                    achievment=achievment+ "\nFats! Fats! Fats!\nReached Fat Intake Goal\n";
                }

                textView.setText(achievment);
            }
        });
        return root;
    }
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
