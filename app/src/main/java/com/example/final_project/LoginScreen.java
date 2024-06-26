package com.example.final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class LoginScreen extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        inputEmail = findViewById(R.id.emailText);
        inputPassword = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerPageButton);

        inputEmail.setTextColor(Color.WHITE);
        inputPassword.setTextColor(Color.WHITE);

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterScreen.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginScreen.this);

            try {
                SQLiteDatabse databaseInstance = new SQLiteDatabse(LoginScreen.this);
                List<UserData> allUsers = databaseInstance.getAllUsers();
                boolean statement = false;

                if (!inputEmail.getText().toString().isEmpty() && !inputPassword.getText().toString().isEmpty()) {
                    for (UserData user : allUsers) {
                        if (inputEmail.getText().toString().equals(user.getEmail()) &&
                                inputPassword.getText().toString().equals(user.getPassword())) {
                            Intent intent = new Intent(getApplicationContext(), ChooseFigure.class);
                            startActivity(intent);
                            statement = false;
                            break;
                        } else {
                            statement = true;
                        }
                    }
                    if (statement) {
                        builder1.setMessage("Account does not exist!");
                        builder1.setCancelable(true);
                        builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                } else {
                    builder1.setMessage("Empty fields!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            } catch (Exception e) {
                builder1.setMessage(e.toString());
                builder1.setCancelable(true);
                builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }


        });
    }
}