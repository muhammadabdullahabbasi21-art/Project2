package com.example.project2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;
    TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        String registeredEmail =
                getIntent().getStringExtra("registered_email");

        if (registeredEmail != null) {
            etEmail.setText(registeredEmail);
        }

        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(this,
                        "Please enter email and password",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            SharedPreferences preferences =
                    getSharedPreferences("LifeSaverData",
                            MODE_PRIVATE);

            String savedEmail =
                    preferences.getString("registered_email", "");

            String savedPassword =
                    preferences.getString("registered_password", "");

            String savedName =
                    preferences.getString("registered_name", "");

            String userName = "";
            String userEmail = "";

            if (email.equals("donor@gmail.com")
                    && password.equals("123456")) {

                userName = "Demo Donor";
                userEmail = "donor@gmail.com";

            } else if (email.equals(savedEmail)
                    && password.equals(savedPassword)
                    && !savedEmail.isEmpty()) {

                userName = savedName;
                userEmail = savedEmail;

            } else {

                Toast.makeText(this,
                        "Invalid email or password",
                        Toast.LENGTH_LONG).show();

                return;
            }

            Intent intent = new Intent(
                    LoginActivity.this,
                    DashboardActivity.class);

            intent.putExtra("user_name", userName);
            intent.putExtra("user_email", userEmail);

            startActivity(intent);
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class);

            startActivity(intent);
        });

        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    ForgotPasswordActivity.class);

            startActivity(intent);
        });
    }
}
