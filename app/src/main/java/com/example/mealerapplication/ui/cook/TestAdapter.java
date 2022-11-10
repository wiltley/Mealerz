package com.example.mealerapplication.ui.cook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mealerapplication.data.rendering.ClickableAdapter;

import java.util.ArrayList;

public class TestAdapter extends ClickableAdapter {


    public TestAdapter(Context context, ArrayList<Object> list, OnElementClickedListener onElementClickedListener, int toInflate) {
        super(context, list, onElementClickedListener, toInflate);
    }


    @NonNull
    @Override
    public ClickableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableAdapter.MyViewHolder holder, int position) {

    }

    public class MyViewHolder extends ClickableAdapter.MyViewHolder{

        public MyViewHolder(@NonNull View itemView, OnElementClickedListener onElementClickedListener) {
            super(itemView, onElementClickedListener);
        }

        @Override
        public void initialize() {

        }

    }

}
