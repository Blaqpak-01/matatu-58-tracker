package com.matatu.tracker58;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
        TextView tvUsername = findViewById(R.id.tvUsername);
        TextView tvUserType = findViewById(R.id.tvUserType);
        TextView tvSubscription = findViewById(R.id.tvSubscription);
        
        // Set user data
        tvUsername.setText("Allan");
        tvUserType.setText("Administrator");
        tvSubscription.setText("Active - Lifetime Access");
    }
}