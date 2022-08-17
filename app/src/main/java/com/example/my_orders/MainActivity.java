package com.example.my_orders;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "test");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "test");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");


        // creating an arraylist

        modelList = new ArrayList<>();
        modelList.add(new Model("Coffee", getString(R.string.latte), R.drawable.late));


        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);
    }



    }