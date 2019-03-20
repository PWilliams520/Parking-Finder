package com.example.noahjarvis.ParkingFinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ParkingLot> lotArray = new ArrayList<>();    //ArrayList of Lots

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.noahjarvis.ParkingFinder.R.layout.activity_main);
        }


    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        //fills ArrayList of lots
        for (int i = 0; i < 100; i++) {
            lotArray.add(new ParkingLot(i));
        }

        setContentView(R.layout.activity_main);
        //ArrayAdapter lotText = new ArrayAdapter<ParkingLot>(this,R.layout.lot_text,lotArray);
        RecyclerView lotList = (RecyclerView) findViewById(R.id.lot_list);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        lotList.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new RecycleViewLotAdapter(lotArray);
        lotList.setAdapter(adapter);

    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
