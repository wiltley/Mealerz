package com.example.mealerapplication.ui.complaints;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapplication.R;

import java.util.ArrayList;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.MyViewHolder>{
    Context context;

    ArrayList<Complaint> list;


    public ComplaintsAdapter(Context context, ArrayList<Complaint> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complaints_item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Complaint complaint = list.get(position);
        holder.accuser.setText(complaint.getAccuser());
        holder.accused.setText(complaint.getAccused());
        holder.message.setText(complaint.getMessage());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView accuser, accused, message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            accuser = itemView.findViewById(R.id.accuser);
            accused = itemView.findViewById(R.id.accused);
            message = itemView.findViewById(R.id.message);

        }
    }
}
