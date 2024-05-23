package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DimensionScreen extends AppCompatActivity {

    TextView titleText;
    EditText dimension1, dimension2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimension_screen);

        titleText = findViewById(R.id.titleText);
        dimension1 = findViewById(R.id.dimensionText);
        dimension2 = findViewById(R.id.dimensionText2);


        Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;

        String title = bundle.getString("title");
        String content = bundle.getString("content");
        String length = bundle.getString("length");
        String width = bundle.getString("width");

        if (title != null) {
            titleText.setText(title);
        }
        if (content != null) {
            dimension1.setHint(content);
        }
        if (length != null && width != null) {
            dimension2.setVisibility(View.VISIBLE);
            dimension1.setHint(length);
            dimension2.setHint(width);
        }
    }
}