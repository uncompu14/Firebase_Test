package com.hanium.firebasetest;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<User> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CustomAdapter.CustomViewHolder holder, int position) {
        holder.r_count.setText(String.valueOf(arrayList.get(position).getCount()));
        holder.r_volt.setText(arrayList.get(position).getVolt());
        holder.r_current.setText(arrayList.get(position).getCurrent());
        holder.r_resistor.setText(arrayList.get(position).getResistor());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView r_volt;
        TextView r_current;
        TextView r_resistor;
        TextView r_count;

        public CustomViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.r_count = itemView.findViewById(R.id.r_count);
            this.r_volt = itemView.findViewById(R.id.r_volt);
            this.r_current = itemView.findViewById(R.id.r_current);
            this.r_resistor = itemView.findViewById(R.id.r_resistor);
        }
    }
}
