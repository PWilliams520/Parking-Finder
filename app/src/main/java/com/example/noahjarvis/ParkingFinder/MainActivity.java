package com.example.noahjarvis.ParkingFinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ParkingLot> lotArray = new ArrayList<>();    //ArrayList of Lots

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            ParkingLot lot = lotArray.get(position);
            Toast.makeText(MainActivity.this, "You Clicked: " + lot.getName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("Current Lot",lot);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.noahjarvis.ParkingFinder.R.layout.activity_main);

        //fills ArrayList of lots
        for (int i = 0; i < 100; i++) {
            lotArray.add(new ParkingLot(i));
        }

        //Creates recycle view and converts ArrayList of Lots to RecyleView
        RecyclerView lotList = findViewById(R.id.lot_list);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        lotList.setLayoutManager(layoutManager);
        RecycleViewLotAdapter adapter = new RecycleViewLotAdapter(this,lotArray);
        lotList.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);
        }


    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();

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
