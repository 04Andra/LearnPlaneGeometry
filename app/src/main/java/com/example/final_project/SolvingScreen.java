package com.example.final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SolvingScreen extends AppCompatActivity {

    TextView formulaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solving_screen);

        formulaText = findViewById(R.id.formulaText);

        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        String circleContent = sharedPreferences.getString("circleContent", "");
        String squareContent = sharedPreferences.getString("squareContent", "");
        String lengthContent = sharedPreferences.getString("lengthContent", "");
        String widthContent = sharedPreferences.getString("widthContent", "");

        String lengthDimension = sharedPreferences.getString("lengthDimension", "");
        String widthDimension = sharedPreferences.getString("widthDimension", "");
        String dimension1 = sharedPreferences.getString("dimension1", "");

        String formula = sharedPreferences.getString("formula", "");

        AlertDialog.Builder builder1 = new AlertDialog.Builder(SolvingScreen.this);


        float solving;

        String text;

        if (!circleContent.equals("")) {
            if (formula.equals("diameter")) {
                try {
                    solving = 2 * Float.parseFloat(dimension1);
                    text = "diameter = 2 * radius = 2 * " + dimension1 + " = " + solving;
                    formulaText.setText(text);
                } catch (NumberFormatException e) {
                    builder1.setMessage("Couldn't transform into float!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            } else if (formula.equals("area")) {
                try {
                    solving = (float) (3.14 * (Float.parseFloat(dimension1) * Float.parseFloat(dimension1)));
                    text = "area = π * radius^2 = 3.14 * " + dimension1 + "^2 = " + solving;
                    formulaText.setText(text);
                } catch (NumberFormatException e) {
                    builder1.setMessage("Couldn't transform into float!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        } else if (!squareContent.equals("")) {
            if (formula.equals("perimeter")) {
                try {
                    solving = 4 * Float.parseFloat(dimension1);
                    text = "perimeter = 4 * side = 4 * " + dimension1 + " = " + solving;
                    formulaText.setText(text);
                } catch (NumberFormatException e) {
                    builder1.setMessage("Couldn't transform into float!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            } else if (formula.equals("area")) {
                try {
                    solving = Float.parseFloat(dimension1) * Float.parseFloat(dimension1);
                    text = "perimeter = side^2 = " + dimension1 + "^2 = " + solving;
                    formulaText.setText(text);
                } catch (NumberFormatException e) {
                    builder1.setMessage("Couldn't transform into float!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        } else if (!lengthContent.equals("") && !widthContent.equals("")) {
            if (formula.equals("perimeter")) {
                try {
                    solving = 2 * (Float.parseFloat(lengthDimension) + Float.parseFloat(widthDimension));
                    text = "perimeter = 2 * (length + width) = 2 * (" + lengthDimension + " + "
                            + widthDimension + ") = " + solving;
                    formulaText.setText(text);
                } catch (NumberFormatException e) {
                    builder1.setMessage("Couldn't transform into float!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            } else if (formula.equals("area")) {
                try {
                    solving = Float.parseFloat(lengthDimension) * Float.parseFloat(widthDimension);
                    text = "area = length * width = " + lengthDimension + " * " + widthDimension
                            + " = " + solving;
                    formulaText.setText(text);
                } catch (NumberFormatException e) {
                    builder1.setMessage("Couldn't transform into float!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        }
    }
}