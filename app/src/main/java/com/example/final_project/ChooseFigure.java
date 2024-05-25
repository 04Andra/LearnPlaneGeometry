package com.example.final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class ChooseFigure extends AppCompatActivity {

    Spinner circleSpinner, squareSpinner, rectangleSpinner;
    Button chooseFigureButton;
    String item, selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_figure);

        circleSpinner = findViewById(R.id.circleSpinner);
        squareSpinner = findViewById(R.id.squareSpinner);
        rectangleSpinner = findViewById(R.id.rectangleSpinner);

        chooseFigureButton = findViewById(R.id.chooseButton);

        ArrayList<String> circleList = new ArrayList<>();
        ArrayList<String> squareList = new ArrayList<>();
        ArrayList<String> rectangleList = new ArrayList<>();

        circleList.add(0, "Circle problem:");
        circleList.add("area");
        circleList.add("diameter");

        squareList.add(0, "Square problem:");
        squareList.add("perimeter");
        squareList.add("area");

        rectangleList.add(0, "Rectangle problem:");
        rectangleList.add("perimeter");
        rectangleList.add("area");

        selected = circleList.get(0);
        item = "";

        circleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if(adapterView.getItemAtPosition(position).equals("Circle problem:")) {
                    System.out.println("categoria selectata");
                } else {
                    item = adapterView.getItemAtPosition(position).toString();
                    selected = circleList.get(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> circleAdapter =
                new ArrayAdapter<>(this, R.layout.checked_spinner_item, circleList);
        circleAdapter.setDropDownViewResource(R.layout.dropdown_spinner_items);
        circleSpinner.setAdapter(circleAdapter);



        squareSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if(adapterView.getItemAtPosition(position).equals("Square problem:")) {
                    System.out.println("categoria selectata");
                } else {
                    item = adapterView.getItemAtPosition(position).toString();
                    selected = squareList.get(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> squareAdapter =
                new ArrayAdapter<>(this, R.layout.checked_spinner_item, squareList);
        squareAdapter.setDropDownViewResource(R.layout.dropdown_spinner_items);
        squareSpinner.setAdapter(squareAdapter);



        rectangleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if(adapterView.getItemAtPosition(position).equals("Rectangle problem:")) {
                    System.out.println("categoria selectata");
                } else {
                    item = adapterView.getItemAtPosition(position).toString();
                    selected = rectangleList.get(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> rectangleAdapter =
                new ArrayAdapter<>(this, R.layout.checked_spinner_item, rectangleList);
        rectangleAdapter.setDropDownViewResource(R.layout.dropdown_spinner_items);
        rectangleSpinner.setAdapter(rectangleAdapter);



        chooseFigureButton.setOnClickListener(v -> {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(ChooseFigure.this);
            SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

            if(Objects.equals(selected, circleList.get(0))) {

                if(!Objects.equals(item, "")) {

                    Intent intent = new Intent(getApplicationContext(), DimensionScreen.class);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("title", "Circle problem");
                    editor.putString("circleContent", "Enter radius dimension...");
                    editor.putString("squareContent", "");
                    editor.putString("lengthContent", "");
                    editor.putString("widthContent", "");
                    editor.putString("formula", item);
                    editor.apply();

                    startActivity(intent);

                } else {
                    builder1.setMessage("You need to choose a problem!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

            } else if(Objects.equals(selected, squareList.get(0))) {


                if(!Objects.equals(item, "")) {

                    Intent intent = new Intent(getApplicationContext(), DimensionScreen.class);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("title", "Square problem");
                    editor.putString("circleContent", "");
                    editor.putString("squareContent", "Enter side of the square...");
                    editor.putString("lengthContent", "");
                    editor.putString("widthContent", "");
                    editor.putString("formula", item);
                    editor.apply();

                    startActivity(intent);

                } else {
                    builder1.setMessage("You need to choose a problem!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }


            } else if(Objects.equals(selected, rectangleList.get(0))) {


                if(!Objects.equals(item, "")) {

                    Intent intent = new Intent(getApplicationContext(), DimensionScreen.class);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("title", "Rectangle problem");
                    editor.putString("circleContent", "");
                    editor.putString("squareContent", "");
                    editor.putString("lengthContent", "Enter length...");
                    editor.putString("widthContent", "Enter width...");
                    editor.putString("formula", item);
                    editor.apply();

                    startActivity(intent);

                } else {
                    builder1.setMessage("You need to choose a problem!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

            }
        });
    }
}