package com.matatu.tracker58;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        
        // Nairobi coordinates
        LatLng nairobi = new LatLng(-1.286389, 36.817223);
        mMap.addMarker(new MarkerOptions().position(nairobi).title("Nairobi CBD"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nairobi, 12));
        
        // Draw Route 58
        drawRoute58();
    }

    private void drawRoute58() {
        LatLng[] route58Points = {
            new LatLng(-1.286389, 36.817223), // CBD
            new LatLng(-1.3000, 36.8300),    // Point 1
            new LatLng(-1.3200, 36.8500),    // Point 2
            new LatLng(-1.3500, 36.8700)     // Point 3
        };

        PolylineOptions routeLine = new PolylineOptions()
                .add(route58Points)
                .width(10f)
                .color(0xFFFF0000);
        
        mMap.addPolyline(routeLine);
    }
}