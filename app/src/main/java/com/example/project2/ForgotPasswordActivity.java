package com.example.project2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends Activity {

    EditText etRecoveryEmail;
    Button btnRecoverPassword, btnBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etRecoveryEmail = findViewById(R.id.etRecoveryEmail);
        btnRecoverPassword = findViewById(R.id.btnRecoverPassword);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);

        btnRecoverPassword.setOnClickListener(v -> {
            String email = etRecoveryEmail.getText().toString().trim();

            if (email.isEmpty()) {
                etRecoveryEmail.setError("Please enter your email");
                etRecoveryEmail.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etRecoveryEmail.setError("Enter a valid email address");
                etRecoveryEmail.requestFocus();
            } else {
                Toast.makeText(this,
                        "Password recovery request received for " + email,
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(
                        ForgotPasswordActivity.this,
                        ResetPasswordActivity.class);

                intent.putExtra("recovery_email", email);
                startActivity(intent);
            }
        });

        btnBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(
                    ForgotPasswordActivity.this,
                    LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
