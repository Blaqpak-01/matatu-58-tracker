package com.matatu.tracker58;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DriverActivity extends AppCompatActivity {
    private TextView tvStatus;
    private Button btnStartTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        
        tvStatus = findViewById(R.id.tvStatus);
        btnStartTrip = findViewById(R.id.btnStartTrip);
        
        btnStartTrip.setOnClickListener(v -> {
            tvStatus.setText("Status: 🟢 Online - Sharing Location");
        });
    }
}