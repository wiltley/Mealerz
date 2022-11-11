package com.example.mealerapplication.ui.complaints;

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

import java.util.ArrayList;

///////// ================== IGNORE REFER TO @TESTADAPTER.JAVA FOR NOW
public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.MyViewHolder>{
    Context context;

    ArrayList<Complaint> list;
    private OnComplaintListener mOnComplaintListener;


    public ComplaintsAdapter(Context context, ArrayList<Complaint> list, OnComplaintListener mOnComplaintListener) {
        this.context = context;
        this.list = list;
        this.mOnComplaintListener = mOnComplaintListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complaints_item,parent,false);
        return  new MyViewHolder(v, mOnComplaintListener);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView accuser, accused, message;
        OnComplaintListener onComplaintListener;

        public MyViewHolder(@NonNull View itemView, OnComplaintListener onComplaintListener) {
            super(itemView);

            this.onComplaintListener = onComplaintListener;
            accuser = itemView.findViewById(R.id.accuser);
            accused = itemView.findViewById(R.id.accused);
            message = itemView.findViewById(R.id.message);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onComplaintListener.onComplaintClicked(getAdapterPosition());

        }
    }

    public interface OnComplaintListener{
        void onComplaintClicked(int position);
    }
}
