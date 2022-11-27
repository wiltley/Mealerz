package com.example.mealerapplication.ui.cook;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.MealRequest;
import com.example.mealerapplication.data.rendering.ClickableAdapter;

import java.util.ArrayList;

public class MySalesAdapter extends ClickableAdapter {

    public MySalesAdapter(Context context, ArrayList list, OnElementClickedListener mOnClickedListener) {
        super(context, list, mOnClickedListener);
    }

    @NonNull
    @Override
    public ClickableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.sale_item,parent,false);
        return  new com.example.mealerapplication.ui.cook.MySalesAdapter.MyViewHolder(v, mOnClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableAdapter.MyViewHolder holder, int position) {

        MealRequest request = (MealRequest) list.get(position);
        holder.text1.setText(request.getMealName());
        holder.text2.setText(request.getClientName());
        holder.text3.setText(request.getClientAddress());

    }


    public class MyViewHolder extends ClickableAdapter.MyViewHolder{

        public MyViewHolder(@NonNull View itemView, OnElementClickedListener onElementClickedListener) {
            // Pass the IDS here
            super(itemView, onElementClickedListener, R.id.sale_meal_name, R.id.sale_client_name, R.id.sale_address);
        }

        @Override
        public void initialize() {

        }

    }
}
