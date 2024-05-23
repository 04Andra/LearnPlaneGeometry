package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            if(Objects.equals(selected, "Circle problem:")) {

                Intent intent = new Intent(getApplicationContext(), DimensionScreen.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", "Circle problem");
                bundle.putString("content", "Enter radius dimension...");

                intent.putExtras(bundle);
                startActivity(intent);

            } else if(Objects.equals(selected, "Square problem:")) {

                Intent intent = new Intent(getApplicationContext(), DimensionScreen.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", "Square problem");
                bundle.putString("content", "Enter side of the square...");

                intent.putExtras(bundle);
                startActivity(intent);

            } else if(Objects.equals(selected, "Rectangle problem:")) {

                Intent intent = new Intent(getApplicationContext(), DimensionScreen.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", "Rectangle problem");
                bundle.putString("length", "Enter length...");
                bundle.putString("width", "Enter width...");

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}