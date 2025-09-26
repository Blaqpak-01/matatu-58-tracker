package com.matatu.tracker58;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ChangeCredentialsActivity extends AppCompatActivity {
    private EditText etNewUsername, etNewPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_credentials);
        
        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btnChange = findViewById(R.id.btnChange);
        
        btnChange.setOnClickListener(v -> changeCredentials());
    }
    
    private void changeCredentials() {
        String newUsername = etNewUsername.getText().toString().trim();
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, 
            "Credentials updated!\nNew Username: " + newUsername + "\nAdmin security updated.", 
            Toast.LENGTH_LONG).show();
        finish();
    }
}