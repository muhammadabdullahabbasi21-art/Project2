package com.example.project2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetPasswordActivity extends Activity {

    EditText etNewPassword, etConfirmNewPassword;
    Button btnResetPassword, btnBackToLogin;
    TextView tvRecoveryEmail;

    String recoveryEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmNewPassword =
                findViewById(R.id.etConfirmNewPassword);

        btnResetPassword = findViewById(R.id.btnResetPassword);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);
        tvRecoveryEmail = findViewById(R.id.tvRecoveryEmail);

        recoveryEmail = getIntent().getStringExtra("recovery_email");

        if (recoveryEmail == null) {
            recoveryEmail = "";
        }

        tvRecoveryEmail.setText("Reset password for: " + recoveryEmail);

        btnResetPassword.setOnClickListener(v -> {

            String newPassword =
                    etNewPassword.getText().toString();

            String confirmPassword =
                    etConfirmNewPassword.getText().toString();

            if (recoveryEmail.isEmpty()) {

                Toast.makeText(this,
                        "Recovery email is missing",
                        Toast.LENGTH_SHORT).show();

            } else if (newPassword.isEmpty()
                    || confirmPassword.isEmpty()) {

                Toast.makeText(this,
                        "Please fill in both password fields",
                        Toast.LENGTH_SHORT).show();

            } else if (newPassword.length() < 6) {

                etNewPassword.setError(
                        "Password must contain at least 6 characters");
                etNewPassword.requestFocus();

            } else if (!newPassword.equals(confirmPassword)) {

                etConfirmNewPassword.setError(
                        "Passwords do not match");
                etConfirmNewPassword.requestFocus();

            } else {

                SharedPreferences preferences =
                        getSharedPreferences(
                                "LifeSaverData", MODE_PRIVATE);

                String savedEmail =
                        preferences.getString(
                                "registered_email", "");

                if (!recoveryEmail.equalsIgnoreCase(savedEmail)) {

                    Toast.makeText(this,
                            "This email is not registered. Please register first.",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                preferences.edit()
                        .putString("registered_password", newPassword)
                        .apply();

                Toast.makeText(this,
                        "Password reset successfully!",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(
                        ResetPasswordActivity.this,
                        LoginActivity.class);

                intent.putExtra("registered_email", recoveryEmail);
                startActivity(intent);
                finish();
            }
        });

        btnBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(
                    ResetPasswordActivity.this,
                    LoginActivity.class);

            startActivity(intent);
            finish();
        });
    }
}
