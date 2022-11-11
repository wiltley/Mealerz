package com.example.mealerapplication.data.rendering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.ui.complaints.ComplaintsAdapter;

import java.util.ArrayList;

public abstract class ClickableAdapter <T> extends RecyclerView.Adapter<ClickableAdapter.MyViewHolder> {

    protected ArrayList<T> list;
    protected Context context;

    protected OnElementClickedListener mOnClickedListener;


    public ClickableAdapter(Context context, ArrayList<T> list, OnElementClickedListener mOnClickedListener){

        this.mOnClickedListener = mOnClickedListener;
        this.list = list;
        this.context= context;

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
        public TextView text1, text2, text3;

        public MyViewHolder(@NonNull View itemView, OnElementClickedListener onElementClickedListener, int id1, int id2, int id3) {
            super(itemView);
            initialize();

            text1 = itemView.findViewById(id1);
            text2 = itemView.findViewById(id2);
            text3 = itemView.findViewById(id3);

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
