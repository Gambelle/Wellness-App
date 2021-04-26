package com.example.test.ui.gallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class AlarmReceiver extends BroadcastReceiver
{
    int waterNum;
    int calNum;
    int carbNum;
    int fatsNum;
    int protienNum;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        SharedPreferences sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("water_pref", 0);
        editor.putInt("calorie_pref", 0);
        editor.putInt("fats_pref", 0);
        editor.putInt("carbs_pref", 0);
        editor.putInt("protien_pref", 0);
        editor.putString("BF_pref", "");
        editor.putString("LC_pref", "");
        editor.putString("DN_pref", "");
    }
}