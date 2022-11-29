package com.example.mealerapplication.ui.cook;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mealerapplication.R;
import androidx.annotation.NonNull;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.MealRequest;
import com.example.mealerapplication.data.rendering.ClickableAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.util.ArrayList;
import java.util.List;

public class MySalesAdapter extends ClickableAdapter {

    List<View> itemViewList = new ArrayList<>();
    public MySalesAdapter(Context context, ArrayList list, OnElementClickedListener mOnClickedListener) {
        super(context, list, mOnClickedListener);
    }


    @NonNull
    @Override
    public ClickableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.sale_item,parent,false);

        final View itemView = LayoutInflater.from(context).inflate(R.layout.sale_item, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(itemView, mOnClickedListener);

        itemViewList.add(itemView); //to add all the 'list row item' views

        //Set on click listener for each item view
        return myViewHolder;
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
