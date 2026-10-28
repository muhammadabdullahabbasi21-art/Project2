package com.example.project2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    EditText etName, etEmail, etPassword, etConfirmPassword;
    Button btnCreateAccount, btnBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);

        btnCreateAccount.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();
            String confirmPassword =
                    etConfirmPassword.getText().toString();

            if (name.isEmpty() || email.isEmpty()
                    || password.isEmpty()
                    || confirmPassword.isEmpty()) {

                Toast.makeText(this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();

            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                etEmail.setError("Enter a valid email address");
                etEmail.requestFocus();

            } else if (password.length() < 6) {

                etPassword.setError(
                        "Password must contain at least 6 characters");
                etPassword.requestFocus();

            } else if (!password.equals(confirmPassword)) {

                etConfirmPassword.setError("Passwords do not match");
                etConfirmPassword.requestFocus();

            } else {

                SharedPreferences preferences =
                        getSharedPreferences("LifeSaverData",
                                MODE_PRIVATE);

                preferences.edit()
                        .putString("registered_name", name)
                        .putString("registered_email", email)
                        .putString("registered_password", password)
                        .apply();

                Toast.makeText(this,
                        "Registration successful! Please log in.",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(
                        RegisterActivity.this,
                        LoginActivity.class);

                intent.putExtra("registered_email", email);
                startActivity(intent);
                finish();
            }
        });

        btnBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(
                    RegisterActivity.this,
                    LoginActivity.class);

            startActivity(intent);
            finish();
        });
    }
}
