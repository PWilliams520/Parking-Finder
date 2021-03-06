package com.example.noahjarvis.ParkingFinder;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//TODO automatically update lot capacity
public class MainActivity extends AppCompatActivity {

    private ArrayList<ParkingLot> lotArray = new ArrayList<>();    //ArrayList of Lots
    private ArrayList<ParkingCapacity> specificParkingLot = new ArrayList<>();
    private DatabaseReference mDatabase;
    private Handler mHandler;
    private String fileName = "lots.csv";
    static boolean isInit = true;


    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            updateAllLots();
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            ParkingLot lot = lotArray.get(position);

            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("Current Lot", (Serializable) lot);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();



        try {
            AssetManager manager = getApplicationContext().getAssets();
            CSVReader reader = new CSVReader(new InputStreamReader(manager.open(fileName)));
            List<String[]> lotData = reader.readAll();
            for(int i = 1; i < lotData.size();i++){
                parseLotString(lotData.get(i));
            }

        }
        catch(IOException e){
            TextView errorText = findViewById(R.id.error);
            errorText.setText("Lots cannot be accessed at this time\n\nTry again later");
        }
        updateAllLots();

        setContentView(com.example.noahjarvis.ParkingFinder.R.layout.activity_main);
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        //Creates recycle view and converts ArrayList of Lots to RecycleView
        RecyclerView lotList = findViewById(R.id.lot_list);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        lotList.setLayoutManager(layoutManager);
        RecycleViewLotAdapter adapter = new RecycleViewLotAdapter(this,lotArray);
        lotList.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable, 100);

        if(isInit) {
            isInit = false;
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }



    }
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            updateAllLots();

            setContentView(com.example.noahjarvis.ParkingFinder.R.layout.activity_main);

            //Creates recycle view and converts ArrayList of Lots to RecycleView
            RecyclerView lotList = findViewById(R.id.lot_list);
            RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(MainActivity.this);
            lotList.setLayoutManager(layoutManager);
            RecycleViewLotAdapter adapter = new RecycleViewLotAdapter(MainActivity.this,lotArray);
            lotList.setAdapter(adapter);
            adapter.setOnItemClickListener(onItemClickListener);
            mHandler = new Handler();
            mHandler.postDelayed(m_Runnable, 1000);
        }

    };//runnable


    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();


    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();



//        setContentView(com.example.noahjarvis.ParkingFinder.R.layout.activity_main);
//        RecyclerView lotList = findViewById(R.id.lot_list);
//        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
//        lotList.setLayoutManager(layoutManager);
//        RecycleViewLotAdapter adapter = new RecycleViewLotAdapter(this,lotArray);
//        lotList.setAdapter(adapter);
//        adapter.setOnItemClickListener(onItemClickListener);

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

    //Parses string from CSV, creates parking lot and adds to lotArray
    private void parseLotString(String[] data){
        if(data.length != 7)
            return;

        int capacity = Integer.parseInt(data[2]);
        int current = Integer.parseInt(data[3]);
        ParkingLot.Type type;
        switch (data[4]){
            case "FS":
                type = ParkingLot.Type.FACULTY;
                break;
            case "V":
                type = ParkingLot.Type.VISITOR;
                break;
            case "G":
                type = ParkingLot.Type.GENERAL;
                break;
            default:
                type = null;
        }
        double lat = Double.parseDouble(data[5]);
        double lon = Double.parseDouble(data[6]);
        lotArray.add(new ParkingLot(data[0],data[1],capacity,current,type,lat,lon));
        ParkingLot temp = lotArray.get(lotArray.size()-1);
        String out = temp.getName() + temp.getDescription() + temp.getCurrent() + temp.getCapacity() + temp.getLatitude() + temp.getLongitude();
        Log.d("LOTS",out);
    }

    private void updateAllLots(){
        retrieveFromFirebase();
        if(lotArray.size() != 0 && specificParkingLot.size() !=0) {
            for (int i = 0; i < lotArray.size(); i++) {
                lotArray.get(i).updateLot(specificParkingLot.get(i).getCurrent());
            }
        }
        specificParkingLot.clear();
        try {
            AssetManager manager = getApplicationContext().getAssets();
            Writer writer = new BufferedWriter(new FileWriter(fileName));
            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            String[] header = {"Name","Description","Capacity","Current","Type","Latitude","Longitude"};
            csvWriter.writeNext(header);
            for(int i = 1; i < lotArray.size();i++){
                csvWriter.writeNext(lotArray.get(i).writeString());
            }

        }catch (IOException e){}
    }

    private void retrieveFromFirebase() {
        Query mQuery = mDatabase.child("Lots");
        mQuery.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ParkingCapacity parkingLotModel = dataSnapshot.getValue(ParkingCapacity.class);
                specificParkingLot.add(parkingLotModel);

                for (int i = 0; i < specificParkingLot.size(); i++)
                    Log.d("PARKING_LOG_TAG", specificParkingLot.get(i).getName());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}