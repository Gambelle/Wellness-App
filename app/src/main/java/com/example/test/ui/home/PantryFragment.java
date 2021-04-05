package com.example.test.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

public class PantryFragment extends Fragment {
    String s1[], s2[];
    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fridge, container, false);

        s1 = getResources().getStringArray(R.array.pantryItems);
        s2 = getResources().getStringArray(R.array.pantryQuantity);
        recyclerView = root.findViewById(R.id.recyclerView);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), s1,s2);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }
}
