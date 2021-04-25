package com.example.test.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    ArrayList<String> data1;
    Context context;

    private OnItemsClickListener listener;

    public RecyclerAdapter(Context ct, ArrayList<String> s1){
        data1 = s1;
        context = ct;
    }

    public void setWhenClickListener(OnItemsClickListener listener){
        this.listener = listener;
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
        String[] dataSplit = data1.get(position).split("@");
        holder.title1.setText(dataSplit[0]);
        holder.title2.setText(dataSplit[1]);
        final int place = position;
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(listener != null){
                    listener.onDeleteClick(place);
                }
            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(listener != null){
                    listener.onEditClick(place,dataSplit[0],dataSplit[1]);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title1, title2;
        ImageButton deleteBtn, editBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.itemNameView);
            title2 = itemView.findViewById(R.id.itemQuantityView);
            deleteBtn = itemView.findViewById(R.id.deleteButton);
            editBtn = itemView.findViewById(R.id.editButton);

        }
    }
    public interface OnItemsClickListener{
        void onDeleteClick(int place);
        void onEditClick(int place, String ItemName, String ItemQuantity);
    }
}
