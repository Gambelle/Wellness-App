package com.example.test.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FridgeFragment extends Fragment {
    private static final String TAG = "FridgeFragment";
    ArrayList<String> s1;
    RecyclerView recyclerView;
    Set<String> defaultString = new HashSet<String>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fridge, container, false);

        ConstraintLayout overlayAdd = (ConstraintLayout) root.findViewById(R.id.additemoverlay);
        ConstraintLayout overlayEdit = (ConstraintLayout) root.findViewById(R.id.edititemoverlay);
        overlayAdd.setVisibility(View.INVISIBLE);
        overlayEdit.setVisibility(View.INVISIBLE);
        TextInputEditText addText1 = root.findViewById(R.id.addItemText1);
        TextInputEditText addText2 = root.findViewById(R.id.addItemText2);
        TextInputEditText editText1 = root.findViewById(R.id.editText1);
        TextInputEditText editText2 = root.findViewById(R.id.editText2);

        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        //s1 = new ArrayList<String> ("Milk@1 gallon", "Eggs@1 dozen", "Smoked Ham@1 pound");
        s1 = new ArrayList<String>(sharedPref.getStringSet(getString(R.string.fridgeItems), defaultString));
        Log.d(TAG,"Imported Fridge List from Shared Preferences: "+s1.toString());
        //s2 = sharedPref.getStringSet(getString(R.string.fridgeQuantities), defaultString).toArray(new String[0]);
        recyclerView = root.findViewById(R.id.recyclerView);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), s1);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ImageButton addItem = (ImageButton) root.findViewById(R.id.addItemButton);
        addItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                overlayAdd.setVisibility(View.VISIBLE);

            }
        });

        ImageButton addItem2 = (ImageButton) root.findViewById(R.id.addItemButton2);
        addItem2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Do something in response to button click
                if(String.valueOf(addText1.getText()) != "" && String.valueOf(addText2.getText()) != "") {
                    Context context = getActivity();
                    SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    s1.add(String.valueOf(addText1.getText()) + "@" + String.valueOf(addText2.getText()));
                    Set<String> s1Set = new HashSet<String>(s1);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putStringSet(getString(R.string.fridgeItems), s1Set);
                    editor.apply();
                    recyclerAdapter.notifyDataSetChanged();
                }
                Log.d(TAG,"Added: "+String.valueOf(addText1.getText()) + "@" + String.valueOf(addText2.getText())+" Fridge list after Add: "+s1.toString());
                overlayAdd.setVisibility(View.INVISIBLE);
                addText1.setText("");
                addText2.setText("");
            }
        });

        ImageButton cancelBtn = (ImageButton) root.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                overlayAdd.setVisibility(View.INVISIBLE);
                addText1.setText("");
                addText2.setText("");
            }
        });

        recyclerAdapter.setWhenClickListener(new RecyclerAdapter.OnItemsClickListener() {
            @Override
            public void onDeleteClick(int place) {

                s1.remove(place);
                Log.d(TAG,"Removal index: "+place+" Fridge list after Removal: "+s1.toString());
                Context context = getActivity();
                SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                Set<String> s1Set = new HashSet<String>(s1);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putStringSet(getString(R.string.fridgeItems), s1Set);
                editor.apply();
                recyclerAdapter.notifyItemRemoved(place);
            }
            @Override
            public void onEditClick(int place, String ItemName, String ItemQuantity){
                overlayEdit.setVisibility(View.VISIBLE);
                editText1.setText(ItemName);
                editText2.setText(ItemQuantity);
                ImageButton editBtn2 = (ImageButton) root.findViewById(R.id.editButton2);
                editBtn2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Do something in response to button click
                        s1.set(place,editText1.getText()+"@"+editText2.getText());
                        Context context = getActivity();
                        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                        Set<String> s1Set = new HashSet<String>(s1);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putStringSet(getString(R.string.fridgeItems), s1Set);
                        editor.apply();
                        Log.d(TAG,"Edit index: "+place+" Fridge list after Edit: "+s1.toString());
                        overlayEdit.setVisibility(View.INVISIBLE);
                        addText1.setText("");
                        addText2.setText("");
                        recyclerAdapter.notifyItemChanged(place);
                    }
                });
            }
        });

        return root;
    }

}
