package com.example.recyclerview;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    CheckBox isSelected;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.nameId);
        isSelected=itemView.findViewById(R.id.selectedCheckBox);


    }
}
