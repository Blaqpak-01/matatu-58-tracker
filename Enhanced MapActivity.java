package com.matatu.tracker58;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nairobi, 12));
        
        // Add current location marker
        mMap.addMarker(new MarkerOptions()
                .position(nairobi)
                .title("Your Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        
        // Show drivers if requested
        if (getIntent().getBooleanExtra("show_drivers", false)) {
            showDriverLocations();
        }
        
        drawRoute58();
    }

    private void showDriverLocations() {
        // Simulate driver locations along Route 58
        LatLng[] driverLocations = {
            new LatLng(-1.2921, 36.8210),
            new LatLng(-1.3000, 36.8300),
            new LatLng(-1.3100, 36.8400),
            new LatLng(-1.3200, 36.8500)
        };
        
        String[] driverNames = {"Driver John", "Driver Mary", "Driver Peter", "Driver Grace"};
        
        for (int i = 0; i < driverLocations.length; i++) {
            mMap.addMarker(new MarkerOptions()
                    .position(driverLocations[i])
                    .title(driverNames[i])
                    .snippet("Route 58 - Online")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
    }

    private void drawRoute58() {
        LatLng[] route58Points = {
            new LatLng(-1.286389, 36.817223), // CBD
            new LatLng(-1.2921, 36.8210),    // Moi Avenue
            new LatLng(-1.3000, 36.8300),    // Uhuru Highway
            new LatLng(-1.3100, 36.8400),    // Langata Road
            new LatLng(-1.3200, 36.8500),    // Karen
            new LatLng(-1.3300, 36.8600),    // Ngong Road
            new LatLng(-1.3400, 36.8700),    // Adams Arcade
            new LatLng(-1.3500, 36.8800)     // Final stop
        };

        PolylineOptions routeLine = new PolylineOptions()
                .add(route58Points)
                .width(12f)
                .color(0xFFFF0000);
        
        mMap.addPolyline(routeLine);
        
        // Add route markers
        mMap.addMarker(new MarkerOptions()
                .position(route58Points[0])
                .title("Route 58 Start")
                .snippet("Nairobi CBD"));
                
        mMap.addMarker(new MarkerOptions()
                .position(route58Points[route58Points.length-1])
                .title("Route 58 End")
                .snippet("Karen Area"));
    }
}