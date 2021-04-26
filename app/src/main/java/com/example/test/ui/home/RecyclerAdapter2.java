package com.example.test.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.ArrayList;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyViewHolder>{

    ArrayList<String> data1;
    Context context;

    public RecyclerAdapter2(Context ct, ArrayList<String> s1){
        data1 = s1;
        context = ct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_food_list, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String[] dataSplit = data1.get(position).split("@");
        holder.title1.setText(dataSplit[0]);
        final int place = position;
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.itemNameViewList);

        }
    }

}
