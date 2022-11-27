package com.example.mealerapplication.ui.client;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.MealRequest;
import com.example.mealerapplication.data.rendering.ClickableAdapter;

import java.util.ArrayList;

public class MyPurchasesAdapter extends ClickableAdapter {

    public MyPurchasesAdapter(Context context, ArrayList list, OnElementClickedListener mOnClickedListener) {
        super(context, list, mOnClickedListener);
    }

    @NonNull
    @Override
    public ClickableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // TODO: 2022-11-23 Change this R.layout to match the meal_request_item
        View v = LayoutInflater.from(context).inflate(R.layout.purchase_item,parent,false);
        return  new com.example.mealerapplication.ui.client.MyPurchasesAdapter.MyViewHolder(v, mOnClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableAdapter.MyViewHolder holder, int position) {

        MealRequest request = (MealRequest) list.get(position);
        holder.text1.setText(request.getMealName());
        holder.text2.setText(request.getCookName());
        holder.text3.setText(request.getStatus());

    }


    public class MyViewHolder extends ClickableAdapter.MyViewHolder{

        public MyViewHolder(@NonNull View itemView, OnElementClickedListener onElementClickedListener) {
            // Pass the IDS here
            // TODO: 2022-11-23  Change these R.IDs to match the meal request item
            super(itemView, onElementClickedListener, R.id.purchase_name, R.id.purchase_cook_name, R.id.purchase_status);
        }

        @Override
        public void initialize() {

        }

    }
}
