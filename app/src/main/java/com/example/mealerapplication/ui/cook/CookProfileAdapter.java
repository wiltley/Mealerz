package com.example.mealerapplication.ui.cook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Review;
import com.example.mealerapplication.data.rendering.ClickableAdapter;

import java.util.ArrayList;

public class CookProfileAdapter extends ClickableAdapter {

    public CookProfileAdapter(Context context, ArrayList list, OnElementClickedListener mOnClickedListener, ArrayList<Review> rev) {
        super(context, list, mOnClickedListener);
    }
    public CookProfileAdapter(Context context, ArrayList list, OnElementClickedListener mOnClickedListener) {
        super(context, list, mOnClickedListener);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.reviews_item, parent, false);
        return new MyViewHolder(v, mOnClickedListener);
    }
    @Override
    public void onBindViewHolder(@NonNull ClickableAdapter.MyViewHolder holder, int position) {
        Review review = (Review) list.get(position);
        holder.text1.setText(review.getFirstName());
        holder.text2.setText(String.valueOf(review.getRating()));
        holder.text3.setText(review.getReviews());
    }


    public class MyViewHolder extends ClickableAdapter.MyViewHolder{

        public MyViewHolder(@NonNull View itemView, OnElementClickedListener onElementClickedListener) {
            // Pass the IDS here
            super(itemView, onElementClickedListener, R.id.fName, R.id.rating, R.id.review_item);
        }

        @Override
        public void initialize() {

        }

    }
}
