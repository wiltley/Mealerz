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
import com.example.mealerapplication.data.model.Complaint;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.data.rendering.ClickableAdapter;
import com.example.mealerapplication.ui.complaints.ComplaintsAdapter;

import java.util.ArrayList;

public class MyMealsAdapter extends ClickableAdapter{

    public MyMealsAdapter(Context context, ArrayList list, OnElementClickedListener mOnClickedListener) {
        super(context, list, mOnClickedListener);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableAdapter.MyViewHolder holder, int position) {

        Recipe recipe = (Recipe) list.get(position);
        holder.text1.setText(recipe.getRecipeName());
        holder.text2.setText(recipe.getAuthor());

    }


    public class MyViewHolder extends ClickableAdapter.MyViewHolder{

        public MyViewHolder(@NonNull View itemView, OnElementClickedListener onElementClickedListener) {
            // Pass the IDS here
            // ============================= NEEDS TO BE CHANGED
            super(itemView, onElementClickedListener, R.id.accuser, R.id.accused, R.id.message);
            // ============================= NEEDS TO BE CHANGED
        }

        @Override
        public void initialize() {

        }

    }
}
