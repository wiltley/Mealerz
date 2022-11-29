package com.example.mealerapplication.data.rendering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.ui.complaints.ComplaintsAdapter;
import com.example.mealerapplication.ui.cook.MySalesAdapter;

import java.util.ArrayList;

// Example usage can be found @TestAdapter class as a substitute for the ComplaintsAdapter
public abstract class ClickableAdapter <T> extends RecyclerView.Adapter<ClickableAdapter.MyViewHolder> {


    // List that holds variable of any type
    // Example usages include Recipe and Complaints
    public ArrayList<T> list;

    // The content must be passed to the constructor as it is used
    // for rendering components of the recycler view
    protected Context context;




    protected OnElementClickedListener mOnClickedListener;


    // An OnElementClickedListener and context object will have to be passed to the constructor
    // It's confusing but all you actually need to do is pass "this" for both of their parameters
    // So usage could be something like
    // TestAdapter myAdapter = new TestAdapter(this, list, this);
    // See more on OnElementClickedListener above ViewHolder class definition

    public ClickableAdapter(Context context, ArrayList<T> list, OnElementClickedListener mOnClickedListener){

        this.mOnClickedListener = mOnClickedListener;
        this.list = list;
        this.context= context;


    }


    // This method HAS to be implemented by classes which inherit from ClickableAdapter
    // Usage typically looks something like
    //         View v = LayoutInflater.from(context).inflate(R.layout.#####-item,parent,false);
    //        return  new MyViewHolder(v, mOnClickedListener);
    // With the #### being the xml file for the component to be displayed in the RecyclerView

    @NonNull
    @Override
    public abstract ClickableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);




    // Also MUST be implemented by classes which inherit from ClickableAdapter
    // Usage typically looks something like
    //  Item item = (Item) list.get(position);
    //        holder.text1.setText(item.getField1());
    //        holder.text2.setText(item.getField2());
    //        holder.text3.setText(item.getField3());
    // You can set MAX up to text3
    @Override
    public abstract void onBindViewHolder(@NonNull ClickableAdapter.MyViewHolder holder, int position);


    // Don't worry about this
    @Override
    public int getItemCount() {
        return list.size();
    }


    // Important notes, classes which implement ClickableAdapter will need to implement
    // OnElementClickedListener

    // For example if I make a ItemAdapter which extends ClickableAdapter
    // In the class where I am using the ItemAdapter, you must do
    // --- implements ItemAdapter.OnElementClickedListener
    public abstract class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnElementClickedListener onElementClickedListener;
        public TextView text1, text2, text3;

        // The int ids are a bit confusing here...
        // Basically when you do findViewById or whatever, they each have a unique integer value associated to them
        // You want to pass the 3 TextViews from your ###_item here.

        public MyViewHolder(@NonNull View itemView, OnElementClickedListener onElementClickedListener, int id1, int id2, int id3) {


            super(itemView);
            initialize();

            text1 = itemView.findViewById(id1);
            text2 = itemView.findViewById(id2);
            text3 = itemView.findViewById(id3);

            this.onElementClickedListener = onElementClickedListener;

            itemView.setOnClickListener(this);
        }

        // Probably not needed, but included just in case you find a use
        // You can just leave the function empty if you do not see a use for it

        public abstract void initialize();

        // Don't worry about this
        @Override
        public void onClick(View v){

            onElementClickedListener.onElementClicked(getAdapterPosition());

        }

    }

    // Refer to note about ViewHolder class definition
    public interface OnElementClickedListener{
        void onElementClicked(int position);
    }


}
