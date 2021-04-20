package com.example.test.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    int num=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        Button btnN=(Button)root.findViewById(R.id.nutrients);
        btnN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.placeholder, new Nutrients());
                fragmentTransaction.commit();

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