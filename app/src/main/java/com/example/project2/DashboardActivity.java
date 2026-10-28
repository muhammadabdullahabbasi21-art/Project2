package com.example.project2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends Activity {

    TextView tvWelcome, tvFindDonors, tvDonateBlood;
    TextView tvBloodRequests, tvMyProfile;

    String userName;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvFindDonors = findViewById(R.id.tvFindDonors);
        tvDonateBlood = findViewById(R.id.tvDonateBlood);
        tvBloodRequests = findViewById(R.id.tvBloodRequests);
        tvMyProfile = findViewById(R.id.tvMyProfile);

        userName = getIntent().getStringExtra("user_name");
        userEmail = getIntent().getStringExtra("user_email");

        if (userName == null || userName.isEmpty()) {
            userName = "Donor";
        }

        if (userEmail == null || userEmail.isEmpty()) {
            userEmail = "donor@gmail.com";
        }

        tvWelcome.setText("Welcome, " + userName + "!");

        tvFindDonors.setOnClickListener(v ->
                Toast.makeText(this, "Find Blood Donors selected",
                        Toast.LENGTH_SHORT).show());

        tvDonateBlood.setOnClickListener(v ->
                Toast.makeText(this, "Donate Blood selected",
                        Toast.LENGTH_SHORT).show());

        tvBloodRequests.setOnClickListener(v ->
                Toast.makeText(this, "Blood Requests selected",
                        Toast.LENGTH_SHORT).show());

        tvMyProfile.setOnClickListener(v -> {
            Intent intent = new Intent(
                    DashboardActivity.this, ProfileActivity.class);

            intent.putExtra("user_name", userName);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });
    }
}
