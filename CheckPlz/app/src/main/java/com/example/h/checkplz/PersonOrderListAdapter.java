package com.example.h.checkplz;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PersonOrderListAdapter extends
        RecyclerView.Adapter<PersonOrderListAdapter.ViewHolder>{

    private OnEditTextChanged onEditTextChanged;
    public static int viewID; //Use to keep tab of EditText being changed so PersonInpuTBillActivity can set the correct value to it's respective array.
    final String PERSON_ORDER_LIST = "person-order-list";
    final String DELETE_ORDER = "delete-order";
    final String UPDATE_ORDER_NAME = "update-order-name";
    final String UPDATE_ORDER_COST = "update-order-cost";
    final String UPDATE_ORDER_AMOUNT = "update-order-amount";

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public EditText personOrderNameEditText;
        public EditText personOrderCostEditText;
        public TextView personOrderMultipleSymbolTextView;
        public EditText personOrderMultipleAmountEditText;
        public ImageView personOrderDelete;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView){
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            personOrderNameEditText = (EditText) itemView.findViewById(R.id.person_order_name);
            personOrderCostEditText = (EditText) itemView.findViewById(R.id.person_order_cost);
            personOrderMultipleSymbolTextView = (TextView) itemView.findViewById(R.id.person_order_multiple_symbol);
            personOrderMultipleAmountEditText = (EditText) itemView.findViewById(R.id.person_order_multiple_amount);
            personOrderDelete = (ImageView) itemView.findViewById(R.id.person_order_delete);
        }
    }

    // Store a member variable for the contacts
    private ArrayList<PersonOrder> mPersonOrderList;

    // Pass in the contact array into the constructor
    public PersonOrderListAdapter(ArrayList<PersonOrder> personOrderList, OnEditTextChanged onEditTextChanged){
        mPersonOrderList=personOrderList;
        this.onEditTextChanged = onEditTextChanged;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PersonOrderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_person_order, parent, false);

        // Return a new holder instance
        PersonOrderListAdapter.ViewHolder viewHolder = new PersonOrderListAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final PersonOrderListAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final PersonOrder personOrder = mPersonOrderList.get(position);

        ImageView imageViewDeleteOrder = viewHolder.personOrderDelete;

        // Set item views based on your views and data model
        EditText editTextOrderName = viewHolder.personOrderNameEditText;
        final EditText editTextOrderCost = viewHolder.personOrderCostEditText;
        final EditText editTextOrderMultipleAmount = viewHolder.personOrderMultipleAmountEditText;

        final Intent intentDelete = new Intent(DELETE_ORDER);
        final Intent intentName = new Intent(UPDATE_ORDER_NAME);
        final Intent intentOrderCost = new Intent(UPDATE_ORDER_COST);
        final Intent intentOrderAmount = new Intent(UPDATE_ORDER_AMOUNT);

        //TODO: Change listener for PersonOrderList name

        //Set text changelistenor for personOrderCostEditText
        viewHolder.personOrderNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //viewID = editTextOrderCost.getId();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //onEditTextChanged.onTextChanged(position, charSequence.toString());
//                Toast.makeText(viewHolder.personOrderNameEditText.getContext(), "Ordername editing onTextChanged", Toast.LENGTH_SHORT ).show();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //set text to person order and update persorOrderlist
//                personOrder.setmOrderName(onEditTextChanged.toString());
//                mPersonOrderList.set(position, personOrder);
//                //TODO:send intent to person Input Bill Activity
//                //TODO:create method for intent being used so all listeners can call it
//                intentName.putParcelableArrayListExtra(PERSON_ORDER_LIST, mPersonOrderList);
//                LocalBroadcastManager.getInstance(viewHolder.personOrderNameEditText.getContext()).sendBroadcast(intentName);
//                Toast.makeText(viewHolder.personOrderNameEditText.getContext(), "Ordername edititing", Toast.LENGTH_SHORT ).show();

            }
        });

        //Set text changelistenor for personOrderCostEditText
        viewHolder.personOrderCostEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                viewID = editTextOrderCost.getId();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                onEditTextChanged.onTextChanged(position, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //set text to person order and update persorOrderlist
//                personOrder.setmOrderCost(Integer.parseInt(onEditTextChanged.toString()));
//                mPersonOrderList.set(position, personOrder);
            }
        });

        //Set text changelistenor for personOrderMultipleAmountEditText
        viewHolder.personOrderMultipleAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                viewID = editTextOrderMultipleAmount.getId();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                onEditTextChanged.onTextChanged(position, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //TODO: update personOrderList position amount

            }
        });


        //Delete order
        imageViewDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = viewHolder.getAdapterPosition();//gets updated position
                //update text view of order cost and amount
                editTextOrderCost.setText("");
                editTextOrderMultipleAmount.setText("");

                //delete item and update person order list
                mPersonOrderList.remove(currentPosition);
                notifyItemRemoved(currentPosition);

                for( int i=0; i<mPersonOrderList.size(); i++) {
                    Log.d("person order list", "Adapter:" + mPersonOrderList.get(i).getmOrderName()+i);
                }
                //broadcast updated mPersonOrderlist
                intentDelete.putExtra("position", currentPosition);
                //new broadcast
                intentDelete.putParcelableArrayListExtra(PERSON_ORDER_LIST, mPersonOrderList);

                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intentDelete);
                Log.d("delete-order-sent", "position " + currentPosition +" sent");


            }
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPersonOrderList.size();
    }

    // Return PersonOrder list
    public ArrayList<PersonOrder> getList() {
        return this.mPersonOrderList;
    }
}
