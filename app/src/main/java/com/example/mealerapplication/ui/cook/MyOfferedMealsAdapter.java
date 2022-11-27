package com.example.mealerapplication.ui.cook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.data.rendering.ClickableAdapter;

import java.util.ArrayList;

public class MyOfferedMealsAdapter extends ClickableAdapter {

    public MyOfferedMealsAdapter(Context context, ArrayList list, ClickableAdapter.OnElementClickedListener mOnClickedListener) {
        super(context, list, mOnClickedListener);
    }

    @NonNull
    @Override
    public MyOfferedMealsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.meal_item,parent,false);
        return  new MyOfferedMealsAdapter.MyViewHolder(v, mOnClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableAdapter.MyViewHolder holder, int position) {

        Recipe recipe = (Recipe) list.get(position);
        holder.text1.setText(recipe.getRecipeName());
        holder.text2.setText(recipe.getCookName());
        holder.text3.setText(String.valueOf(recipe.getPrice() + "$"));

    }


    public class MyViewHolder extends ClickableAdapter.MyViewHolder{

        public MyViewHolder(@NonNull View itemView, ClickableAdapter.OnElementClickedListener onElementClickedListener) {
            // Pass the IDS here
            super(itemView, onElementClickedListener, R.id.meal_name, R.id.meal_cook_name, R.id.meal_price);
        }

        @Override
        public void initialize() {

        }

    }
}
