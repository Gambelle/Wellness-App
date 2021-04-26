package com.example.test.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.ArrayList;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyViewHolder>{

    ArrayList<String> data1;
    Context context;

    private OnItemsClickListener listener;

    public RecyclerAdapter2(Context ct, ArrayList<String> s1){
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
        View view = inflater.inflate(R.layout.item_food_list, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String[] dataSplit = data1.get(position).split("@");
        holder.title1.setText(dataSplit[0]);
        final int place = position;
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(listener != null){
                    listener.onCheck(dataSplit[0], holder.checkBox.isChecked());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title1;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.itemNameViewList);
            checkBox = itemView.findViewById(R.id.checkBoxList);
        }
    }
    public interface OnItemsClickListener{
        void onCheck(String ItemName, boolean checked);
    }

}
