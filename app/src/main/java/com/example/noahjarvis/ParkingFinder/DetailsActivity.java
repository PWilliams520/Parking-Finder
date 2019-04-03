package com.example.noahjarvis.ParkingFinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ParkingLot lot = (ParkingLot) getIntent().getSerializableExtra("Current Lot");
        setTitle(lot.getName());
        LinearLayout layout= new LinearLayout(this);
        setContentView(layout);
        TextView test = new TextView(this);
        test.setText(lot.toString());
        layout.addView(test);


    }
}
