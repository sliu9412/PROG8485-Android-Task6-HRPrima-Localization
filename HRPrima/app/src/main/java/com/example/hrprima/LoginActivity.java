package com.example.hrprima;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hrprima.databinding.ActivityLoginBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding loginBinding;
    Intent intent2;
    SharedPreferences sharedPref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        init();
    }

    private void init() {
        loginBinding.btnLogin.setOnClickListener(this);
        // prepare sharePreference for the whole application
        sharedPref1 = getSharedPreferences("login_details", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == loginBinding.btnLogin.getId()) {
            String username = loginBinding.edtUserId.getText().toString().trim();
            String password = loginBinding.edtPasswd.getText().toString().trim();
            String email = loginBinding.edtEmailid.getText().toString().trim();
            if (username.equals("siyu") && password.equals("test")) {

                SharedPreferences.Editor editor = sharedPref1.edit();
                editor.putString("USER_ID", username);
                editor.putString("EMAIL_ID", email);
                editor.commit();

//                intent2 = new Intent(this, TestHomeActivity.class);
                intent2 = new Intent(this, HomeActivity.class);
                startActivity(intent2);
            } else {
//                LoginFailDialog loginFailDialog = new LoginFailDialog();
//                loginFailDialog.show(getSupportFragmentManager(), "failed");
//                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();

                // material dialog
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Login Failed")
                        .setMessage("Invalid User ID and/or Password")
                        .setNegativeButton("Cancel", (dialog, which) -> {
                        })
                        .setPositiveButton("Ok", (dialog, which) -> {

                        })
                        .show();
            }
        }
    }
}