package com.matatu.tracker58;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    
    private EditText etNewUsername, etNewPassword;
    private Button btnUpdateCredentials, btnViewSubscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnUpdateCredentials = findViewById(R.id.btnUpdateCredentials);
        btnViewSubscriptions = findViewById(R.id.btnViewSubscriptions);

        btnUpdateCredentials.setOnClickListener(v -> updateAdminCredentials());
        btnViewSubscriptions.setOnClickListener(v -> viewSubscriptions());
    }

    private void updateAdminCredentials() {
        String newUsername = etNewUsername.getText().toString();
        String newPassword = etNewPassword.getText().toString();

        if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("admin_username", newUsername);
            editor.putString("admin_password", newPassword);
            editor.apply();

            Toast.makeText(this, "Credentials updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewSubscriptions() {
        // Implementation for viewing subscriptions
        Toast.makeText(this, "Subscription view feature", Toast.LENGTH_SHORT).show();
    }
}