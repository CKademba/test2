package com.example.mytrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


   // Deklariere notwendige Ansichten und Hilfsklassen
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisiere DBHelper-Klasse für die Interaktion mit der Datenbank
        dbHelper = new DBHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Setze OnClickListener für den Login-Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hole eingegebenen Benutzernamen und das Passwort
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Überprüfe, ob Benutzerdaten in der Datenbank existieren
                if (dbHelper.checkUser(username, password)) {
                    // Wenn die Benutzerdaten korrekt sind, starte WelcomeActivity
                    Intent intent = new Intent
                            (LoginActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Wenn die Benutzerdaten falsch sind, zeige Fehlermeldung an
                    Toast.makeText(LoginActivity.this, "Username or Password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Setze OnClickListener für den Registrieren-Button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrierenActivity.class);
                startActivity(intent);
                finish();
            }

        });


    }
}