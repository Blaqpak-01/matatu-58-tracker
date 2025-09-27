package com.matatu.tracker58;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    
    private EditText etUsername, etPassword;
    private Button btnLogin, btnBiometric;
    private String userType;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBiometric = findViewById(R.id.btnBiometric);

        userType = getIntent().getStringExtra("userType");
        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        if ("admin".equals(userType)) {
            setupBiometricAuth();
        } else {
            btnBiometric.setVisibility(android.view.View.GONE);
        }

        btnLogin.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (validateCredentials(username, password)) {
            navigateToUserDashboard();
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateCredentials(String username, String password) {
        if ("admin".equals(userType)) {
            return "Allan".equals(username) && "Liam-0205!".equals(password);
        }
        // For drivers and passengers, check against stored credentials
        String storedUser = sharedPreferences.getString(userType + "_username", "");
        String storedPass = sharedPreferences.getString(userType + "_password", "");
        return username.equals(storedUser) && password.equals(storedPass);
    }

    private void navigateToUserDashboard() {
        Intent intent;
        switch (userType) {
            case "admin":
                intent = new Intent(this, AdminActivity.class);
                break;
            case "driver":
                intent = new Intent(this, DriverActivity.class);
                break;
            case "passenger":
                intent = new Intent(this, PassengerActivity.class);
                break;
            default:
                return;
        }
        startActivity(intent);
        finish();
    }

    private void setupBiometricAuth() {
        Executor executor = Executors.newSingleThreadExecutor();
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, 
            new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                    runOnUiThread(() -> {
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(intent);
                        finish();
                    });
                }
            });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
            .setTitle("Admin Biometric Login")
            .setSubtitle("Use your fingerprint to login as admin")
            .setNegativeButtonText("Cancel")
            .build();

        btnBiometric.setOnClickListener(v -> biometricPrompt.authenticate(promptInfo));
    }
}