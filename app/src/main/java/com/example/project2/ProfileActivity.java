package com.example.project2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;

public class ProfileActivity extends Activity {

    TextView tvProfileName, tvProfileEmail, tvBloodGroup;
    Button btnLogout, btnBackToDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileEmail = findViewById(R.id.tvProfileEmail);
        tvBloodGroup = findViewById(R.id.tvBloodGroup);
        btnLogout = findViewById(R.id.btnLogout);
        btnBackToDashboard = findViewById(R.id.btnBackToDashboard);

        String name = getIntent().getStringExtra("user_name");
        String email = getIntent().getStringExtra("user_email");

        if (name == null || name.isEmpty()) {
            name = "Donor";
        }

        if (email == null || email.isEmpty()) {
            email = "donor@gmail.com";
        }

        tvProfileName.setText("Name: " + name);
        tvProfileEmail.setText("Email: " + email);
        tvBloodGroup.setText("Blood Group: Not provided");

        btnBackToDashboard.setOnClickListener(v -> {
            finish();
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(
                    ProfileActivity.this, MainActivity.class);

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            finish();
        });
    }
}
