package com.example.test.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    String data1[], data2[];
    Context context;

    public RecyclerAdapter(Context ct, String[] s1, String[] s2){
        data1 = s1;
        data2 = s2;
        context = ct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_food, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title1.setText(data1[position]);
        holder.title2.setText(data2[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title1, title2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.itemNameView);
            title2 = itemView.findViewById(R.id.itemQuantityView);

        }
    }
}
