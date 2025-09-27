package com.matatu.tracker58;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SubscriptionActivity extends AppCompatActivity {
    
    private TextView tvSubscriptionInfo;
    private Button btnSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        tvSubscriptionInfo = findViewById(R.id.tvSubscriptionInfo);
        btnSubscribe = findViewById(R.id.btnSubscribe);

        String userType = getIntent().getStringExtra("userType");
        int amount = "driver".equals(userType) ? 500 : 200;

        tvSubscriptionInfo.setText(String.format(
            "Monthly Subscription: Kshs %d\nMPesa Pochi La Biashara: 0708888630", amount));

        btnSubscribe.setOnClickListener(v -> initiateMpesaPayment(amount));
    }

    private void initiateMpesaPayment(int amount) {
        String mpesaUri = String.format("mpesa://paybusiness?amount=%d&recipient=0708888630", amount);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mpesaUri));
        
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Fallback to USSD
            String ussdCode = String.format("*144*1*1*0708888630*%d#", amount);
            Intent ussdIntent = new Intent(Intent.ACTION_DIAL);
            ussdIntent.setData(Uri.parse("tel:" + Uri.encode(ussdCode)));
            startActivity(ussdIntent);
        }
    }
}