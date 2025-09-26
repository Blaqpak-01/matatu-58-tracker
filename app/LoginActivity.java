package com.matatu.tracker58;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.matatu.tracker58.database.DatabaseHelper;
import com.matatu.tracker58.models.User;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private RadioGroup rgUserType;
    private Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        rgUserType = findViewById(R.id.rgUserType);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    private void setupClickListeners() {
        btnLogin.setOnClickListener(v -> handleLogin());
        btnSignUp.setOnClickListener(v -> startActivity(new Intent(this, SignUpActivity.class)));
    }

    private void handleLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String userType = getSelectedUserType();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        User user = dbHelper.getUser(username, password);
        
        if (user != null && user.getUserType().equals(userType)) {
            redirectToDashboard(userType);
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private String getSelectedUserType() {
        int selectedId = rgUserType.getCheckedRadioButtonId();
        if (selectedId == R.id.rbAdmin) return "admin";
        if (selectedId == R.id.rbDriver) return "driver";
        if (selectedId == R.id.rbPassenger) return "passenger";
        return "";
    }

    private void redirectToDashboard(String userType) {
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
}
