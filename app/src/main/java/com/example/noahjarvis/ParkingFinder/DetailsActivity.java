package com.example.noahjarvis.ParkingFinder;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap lotMap;
    private ParkingLot lot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lot = (ParkingLot) getIntent().getSerializableExtra("Current Lot");

        TextView mapTitle = findViewById(R.id.map_title);
        mapTitle.setText(lot.getName());

        TextView percentText = findViewById(R.id.map_percent);
        String text = lot.toString() + " spots taken";
        if(lot.getCurrent() < 0)
            text = "lot capacity unknown";
        percentText.setText(text);

        TextView description = findViewById(R.id.lot_descript);
        description.setText(lot.getDescription());

        TextView type = findViewById(R.id.lot_type);
        String typeText = "Unknown Lot Restrictions";
        if(lot.getType() == ParkingLot.Type.FACULTY){
            typeText = "Faculty, Student Lot";
        }
        else if(lot.getType() == ParkingLot.Type.VISITOR){
            typeText = "Visitor Lot";
        }
        else if(lot.getType() == ParkingLot.Type.GENERAL) {
            typeText = "General Use Lot";
        }
        type.setText(typeText);
        setTitle(lot.getName());







    }

    public void onMapReady(GoogleMap googleMap) {
        lotMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng lotLocation = new LatLng(lot.getLatitude(), lot.getLongitude());
        lotMap.addMarker(new MarkerOptions().position(lotLocation).title(lot.getName()));
        lotMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lotLocation,18));
    }
}
