package com.matatu.tracker58;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etConfirmPassword, etPhone;
    private RadioGroup rgUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etPhone = findViewById(R.id.etPhone);
        rgUserType = findViewById(R.id.rgUserType);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        
        btnSignUp.setOnClickListener(v -> handleSignUp());
    }
    
    private void handleSignUp() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String userType = getSelectedUserType();

        if (username.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userType.isEmpty()) {
            Toast.makeText(this, "Please select user type", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Account created! Please subscribe.", Toast.LENGTH_SHORT).show();
        finish();
    }
    
    private String getSelectedUserType() {
        int selectedId = rgUserType.getCheckedRadioButtonId();
        if (selectedId == R.id.rbDriver) return "driver";
        if (selectedId == R.id.rbPassenger) return "passenger";
        return "";
    }
}