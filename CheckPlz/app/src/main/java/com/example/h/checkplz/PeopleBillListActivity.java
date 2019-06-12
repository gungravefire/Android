package com.example.h.checkplz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PeopleBillListActivity extends AppCompatActivity implements PeopleBillListAdapter.OnBillListener{
    final String PERSON_ORDER_BILL = "person-order-bill";
    final String PERSON_ORDER_BILL_EDIT = "person-order-bill-edit";
    final String PERSON_ORDER_BILL_POSITION = "person-order-bill-position";
    public static final String MY_BILL_LIST = "MyBillList";
    ArrayList<PersonBill> mPeopleBills;
    private RecyclerView rvPeopleBillList;
    private PeopleBillListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_bill_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PeopleBillListActivity");

        loadData();
        buildRecyclerView();
        updateData();

    }



    //Save the state of bill list
    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mPeopleBills);
        editor.putString(MY_BILL_LIST, json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(MY_BILL_LIST, null);
        Type type = new TypeToken<ArrayList<PersonBill>>() {}.getType();
        mPeopleBills = gson.fromJson(json, type);

        if (mPeopleBills == null) {
            mPeopleBills = new ArrayList<>();
        }
    }

    private void updateData() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){//TODO: Organize: create method for if statement
            int personOrderBillPos = extras.getInt(PERSON_ORDER_BILL_POSITION);
            PersonBill mPersonBill = extras.getParcelable(PERSON_ORDER_BILL);
            if(personOrderBillPos>=0){//check if return person bill had pos if so edit else add
                mPeopleBills.set(personOrderBillPos, mPersonBill);
            }
            else{
                mPeopleBills.add(mPersonBill);
            }
        }
    }

    private void buildRecyclerView() {
        rvPeopleBillList = findViewById(R.id.rv_people_bill_list);
        rvPeopleBillList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PeopleBillListAdapter(mPeopleBills, this);//this can be used due to implements PeopleBillListAdapter.OnBillListener. Pass this OnBillListener to adapter

        rvPeopleBillList.setLayoutManager(mLayoutManager);
        rvPeopleBillList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.enter_person_input_bill_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this, PersonInputBillActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBillClick(int position) {
        Log.d("OnBillClick", "PersonOrdersSize:" + String.valueOf(mPeopleBills.get(position).getmPersonOrders().size()));

        Intent intent = new Intent(this, PersonInputBillActivity.class);
        intent.putExtra(PERSON_ORDER_BILL_EDIT, mPeopleBills.get(position));//Send personbill
        intent.putExtra(PERSON_ORDER_BILL_POSITION, position);//send the position of person bill to be able to edit the correct bill
        startActivity(intent);
    }
}
