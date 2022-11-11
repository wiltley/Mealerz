package com.example.mealerapplication.ui.cook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Complaint;
import com.example.mealerapplication.data.rendering.ClickableAdapter;

import java.util.ArrayList;

public class TestAdapter extends ClickableAdapter {


    public TestAdapter(Context context, ArrayList<Complaint> list, OnElementClickedListener mOnClickedListener) {
        super(context, list, mOnClickedListener);
    }


    @NonNull
    @Override
    public ClickableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.complaints_item,parent,false);
        return  new MyViewHolder(v, mOnClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableAdapter.MyViewHolder holder, int position) {

        Complaint complaint = (Complaint) list.get(position);
        holder.text1.setText(complaint.getAccuser());
        holder.text2.setText(complaint.getAccused());
        holder.text3.setText(complaint.getMessage());

    }

    public class MyViewHolder extends ClickableAdapter.MyViewHolder{

        public MyViewHolder(@NonNull View itemView, OnElementClickedListener onElementClickedListener) {
            // Pass the IDS here
            super(itemView, onElementClickedListener, R.id.accuser, R.id.accused, R.id.message);
        }

        @Override
        public void initialize() {

        }

    }

}
