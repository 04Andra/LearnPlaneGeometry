package com.example.final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegisterScreen extends AppCompatActivity {

    EditText inputEmail, inputPassword, inputRePassword;
    Button loginButton, registerButton;
    List<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        inputEmail = (EditText) findViewById(R.id.emailText);
        inputPassword = (EditText) findViewById(R.id.passwordText);
        inputRePassword = (EditText) findViewById(R.id.rePasswordText);
        loginButton = (Button) findViewById(R.id.loginPageButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        inputEmail.setTextColor(Color.WHITE);
        inputPassword.setTextColor(Color.WHITE);
        inputRePassword.setTextColor(Color.WHITE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserData userData;
                int contor = 0;
                boolean userExists = false;

                SQLiteDatabse databaseInstance = new SQLiteDatabse(RegisterScreen.this);
                List<UserData> allUsers = databaseInstance.getAllUsers();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterScreen.this);

                if (!inputEmail.getText().toString().isEmpty()
                        && !inputPassword.getText().toString().isEmpty()
                        && !inputRePassword.getText().toString().isEmpty()) {
                    if (!inputPassword.getText().toString().equals(inputRePassword.getText().toString())) {
                        contor = 1;
                    } else {
                        for (UserData user : allUsers) {
                            if (inputEmail.getText().toString().equals(user.getEmail()) &&
                                    inputPassword.getText().toString().equals(user.getPassword())) {
                                userExists = true;
                                contor = 2;
                                break;
                            }
                        }

                        if (!userExists) {
                            try {
                                userData = new UserData(-1, inputEmail.getText().toString(),
                                        inputPassword.getText().toString());
                                if (databaseInstance.addRecord(userData)) {
                                    contor = 3;
                                } else {
                                    Toast.makeText(RegisterScreen.this,
                                            "Error adding user to database",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Toast.makeText(RegisterScreen.this, "Error creating user",
                                        Toast.LENGTH_SHORT).show();
                                userData = new UserData(-1, "error", "error");
                            }
                        }
                    }

                    if (contor == 3) {
                        Intent intent = new Intent(getApplicationContext(), ChooseFigure.class);
                        startActivity(intent);
                    } else {
                        if (contor == 1) {
                            builder1.setMessage("Passwords are not equal!");
                        } else if (contor == 2) {
                            builder1.setMessage("Account already exists!");
                        }
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
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(intent);
            }
        });
    }
}