package com.example.h.checkplz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class PeopleBillListAdapter extends
        RecyclerView.Adapter<PeopleBillListAdapter.ViewHolder> {


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView tipTextView;
        public TextView taxTextView;
        public TextView totalTextView;
        public TextView tipAmountTextView;
        public TextView taxAmountTextView;
        public TextView totalAmountTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView){
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.person_name);
            tipTextView = (TextView) itemView.findViewById(R.id.person_tip);
            taxTextView = (TextView) itemView.findViewById(R.id.person_tax);
            totalTextView = (TextView) itemView.findViewById(R.id.person_total);
            tipAmountTextView = (TextView) itemView.findViewById(R.id.person_tip_amount);
            taxAmountTextView = (TextView) itemView.findViewById(R.id.person_tax_amount);
            totalAmountTextView = (TextView) itemView.findViewById(R.id.person_total_amount);

        }
    }

    // Store a member variable for the contacts
    private List<PersonBill> mPeopleBillList;

    // Pass in the contact array into the constructor
    public PeopleBillListAdapter(List<PersonBill> peopleBillList){
        mPeopleBillList=peopleBillList;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PeopleBillListAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_person, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder( PeopleBillListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        PersonBill personBill = mPeopleBillList.get(position);

        // Set item views based on your views and data model
        TextView textViewName = viewHolder.nameTextView;
        textViewName.setText(personBill.getmName());
        TextView textViewTip = viewHolder.tipAmountTextView;
        textViewTip.setText(personBill.getmTip());
        TextView textViewTax = viewHolder.taxAmountTextView;
        textViewTax.setText(personBill.getmTax());
        TextView textViewTotal = viewHolder.totalAmountTextView;
        textViewTotal.setText(personBill.getmTotalBill());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPeopleBillList.size();
    }

}