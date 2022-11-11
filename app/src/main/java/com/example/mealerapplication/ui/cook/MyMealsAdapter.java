package com.example.mealerapplication.ui.cook;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.ui.complaints.ComplaintsAdapter;

import java.util.ArrayList;

public class MyMealsAdapter extends RecyclerView.Adapter<MyMealsAdapter.MyViewHolder> {

    Context context;
    ArrayList<Recipe> recipes;

    public MyMealsAdapter(Context context, ArrayList<Recipe> list){

        recipes = list;

    }

    @NonNull
    @Override
    public MyMealsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.complaints_item,parent,false);
        return  new MyMealsAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMealsAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView recipe;
        TextView ingredients;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //recipe = itemView.findViewById(R.id.recipe);
            //ingredients = itemView.findViewById(R.id.ingredients);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
