package com.example.testinterview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);

        btnLogin.setOnClickListener(v -> {
            if (isValidation()){
                startActivity(new Intent());
            }


        });


    }


    private boolean isValidation(){
        if (edUsername.getText().toString().trim().isEmpty()){
            edUsername.setError("Harap isi Email Anda");
            return false;
        }

        if (edPassword.getText().toString().trim().isEmpty()){
            edPassword.setError("Harap isi Password Anda");
            return false;
        }

        return true;
    }


}