package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<Item> itemList;

    public MyAdapter( List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(itemList.get(position).getName());
        holder.isSelected.setChecked(itemList.get(position).isSelected());

        // Центрування елементів з параметром `true`

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
