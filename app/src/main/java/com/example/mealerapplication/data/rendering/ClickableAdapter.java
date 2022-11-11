package com.example.mealerapplication.data.rendering;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapplication.R;

import java.util.ArrayList;

public abstract class ClickableAdapter extends RecyclerView.Adapter<ClickableAdapter.MyViewHolder> {

    ArrayList<Object> list;
    public int toInflate;

    private OnElementClickedListener mOnClickedListener;


    public ClickableAdapter(Context context, ArrayList<Object> list, OnElementClickedListener mOnClickedListener, int toInflate){

        this.mOnClickedListener = mOnClickedListener;
        this.list = list;
        this.toInflate = toInflate;

    }



    @NonNull
    @Override
    public abstract ClickableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);


    @Override
    public abstract void onBindViewHolder(@NonNull ClickableAdapter.MyViewHolder holder, int position);


    @Override
    public int getItemCount() {
        return list.size();
    }

    public abstract class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        OnElementClickedListener onElementClickedListener;

        public MyViewHolder(@NonNull View itemView, OnElementClickedListener onElementClickedListener) {
            super(itemView);
            initialize();
            this.onElementClickedListener = onElementClickedListener;

            itemView.setOnClickListener(this);
        }

        public abstract void initialize();

        @Override
        public void onClick(View v){

            onElementClickedListener.onElementClicked(getAdapterPosition());

        }

    }

    public interface OnElementClickedListener{
        void onElementClicked(int position);
    }
}
