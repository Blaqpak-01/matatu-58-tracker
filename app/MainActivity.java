package com.matatu.tracker58;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Redirect to Login Activity
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
