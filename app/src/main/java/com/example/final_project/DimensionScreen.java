package com.example.final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DimensionScreen extends AppCompatActivity {

    TextView titleText;
    EditText dimension1, dimension2;
    Button solveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimension_screen);

        titleText = findViewById(R.id.titleText);
        dimension1 = findViewById(R.id.dimensionText);
        dimension2 = findViewById(R.id.dimensionText2);
        solveButton = findViewById(R.id.solveButton);

        dimension1.setTextColor(Color.WHITE);
        dimension2.setTextColor(Color.WHITE);

        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        String title = sharedPreferences.getString("title", "");
        String circleContent = sharedPreferences.getString("circleContent", "");
        String squareContent = sharedPreferences.getString("squareContent", "");
        String lengthContent = sharedPreferences.getString("lengthContent", "");
        String widthContent = sharedPreferences.getString("widthContent", "");

        if (!title.equals("")) {
            titleText.setText(title);
        }
        if (!circleContent.equals("")) {
            dimension1.setHint(circleContent);
        } else if (!squareContent.equals("")) {
            dimension1.setHint(squareContent);
        }
        if (!lengthContent.equals("") && !widthContent.equals("")) {
            dimension2.setVisibility(View.VISIBLE);
            dimension1.setHint(lengthContent);
            dimension2.setHint(widthContent);
        }

        solveButton.setOnClickListener(v -> {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(DimensionScreen.this);

            if (!circleContent.equals("") || !squareContent.equals("")) {
                if (!dimension1.getText().toString().trim().isEmpty()) {


                    try {
                        if (Float.parseFloat(String.valueOf(dimension1.getText())) == 0) {
                            builder1.setMessage("Input mustn't be 0!");
                            builder1.setCancelable(true);
                            builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("dimension1", dimension1.getText().toString().trim());
                            editor.putString("lengthDimension", "");
                            editor.putString("widthDimension", "");
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), SolvingScreen.class);
                            startActivity(intent);
                        }
                    } catch (NumberFormatException e) {
                        builder1.setMessage("Input must be a valid number!");
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
            } else if (!lengthContent.equals("") && !widthContent.equals("")) {
                if (!dimension1.getText().toString().trim().isEmpty() &&
                        !dimension2.getText().toString().trim().isEmpty()) {
                    try {
                        if (Float.parseFloat(String.valueOf(dimension1.getText())) == 0
                                || Float.parseFloat(String.valueOf(dimension2.getText())) == 0) {
                            builder1.setMessage("Input mustn't be 0!");
                            builder1.setCancelable(true);
                            builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("dimension1", "");
                            editor.putString("lengthDimension", dimension1.getText().toString().trim());
                            editor.putString("widthDimension", dimension2.getText().toString().trim());
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), SolvingScreen.class);
                            startActivity(intent);

                        }
                    } catch (NumberFormatException e) {
                        builder1.setMessage("Input must be a valid number!");
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
    }
}