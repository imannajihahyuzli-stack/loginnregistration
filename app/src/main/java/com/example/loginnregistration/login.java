package com.example.loginnregistration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText etEmail, etPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp);
        Button btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmailAddress);
        etPass = findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(v -> loginUser());

        tvRedirectSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(login.this, "Email and Password cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(login.this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(login.this, "Log In failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
