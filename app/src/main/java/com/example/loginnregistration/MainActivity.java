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

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPass, etConfPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etSEmailAddress);
        etPass = findViewById(R.id.etSPassword);
        etConfPass = findViewById(R.id.etSConfPassword);
        Button btnSignUp = findViewById(R.id.btnSSigned);
        TextView tvRedirectLogin = findViewById(R.id.tvRedirectLogin);

        mAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(v -> signUpUser());

        tvRedirectLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
        });
    }

    private void signUpUser() {
        String email = etEmail.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        String confirmPassword = etConfPass.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(MainActivity.this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(confirmPassword)) {
            Toast.makeText(MainActivity.this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(MainActivity.this, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Signed Up Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
