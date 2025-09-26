package com.matatu.tracker58;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PassengerActivity extends AppCompatActivity {
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        
        tvStatus = findViewById(R.id.tvStatus);
        Button btnFindMatatu = findViewById(R.id.btnFindMatatu);
        Button btnViewMap = findViewById(R.id.btnViewMap);
        
        btnFindMatatu.setOnClickListener(v -> {
            tvStatus.setText("🔍 Finding nearest Matatu 58...");
            // Simulate search
            new android.os.Handler().postDelayed(() -> {
                tvStatus.setText("✅ Nearest Matatu: 5 mins away");
            }, 2000);
        });
        
        btnViewMap.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapActivity.class);
            intent.putExtra("show_drivers", true);
            startActivity(intent);
        });
    }
}