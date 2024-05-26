package com.example.final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SolvingScreen extends AppCompatActivity {

    TextView formulaText, seekBarText, seekBarText2, minText, maxText, minText2, maxText2;
    ImageView imageView;
    SeekBar seekBar, seekBar2;
    String dimension1, widthDimension, lengthDimension, text;
    float solving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solving_screen);

        formulaText = findViewById(R.id.formulaText);
        seekBarText = findViewById(R.id.seekBarText);
        seekBarText2 = findViewById(R.id.seekBarText2);
        imageView = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);
        seekBar2 = findViewById(R.id.seekBar2);
        minText = findViewById(R.id.minText);
        minText2 = findViewById(R.id.minText2);
        maxText = findViewById(R.id.maxText);
        maxText2 = findViewById(R.id.maxText2);


        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        String circleContent = sharedPreferences.getString("circleContent", "");
        String squareContent = sharedPreferences.getString("squareContent", "");
        String lengthContent = sharedPreferences.getString("lengthContent", "");
        String widthContent = sharedPreferences.getString("widthContent", "");

        lengthDimension = sharedPreferences.getString("lengthDimension", "");
        widthDimension = sharedPreferences.getString("widthDimension", "");
        dimension1 = sharedPreferences.getString("dimension1", "");

        String formula = sharedPreferences.getString("formula", "");

        seekBar.setMax(700);
        seekBar.setProgress(200);
        seekBar.setMin(100);

        seekBar2.setMax(700);
        seekBar2.setProgress(200);
        seekBar2.setMin(100);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(SolvingScreen.this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageView.getLayoutParams().height = progress;
                imageView.getLayoutParams().width = progress;
                imageView.requestLayout();

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                String text = "" + progress;
                seekBarText.setText(text);
                seekBarText.setX(seekBar.getX() + val + (float) seekBar.getThumbOffset() / 2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (!dimension1.isEmpty()) {
                    editor.putString("dimension1", "" + seekBar.getProgress());
                    editor.apply();

                    dimension1 = sharedPreferences.getString("dimension1", "");

                } else if (!lengthDimension.isEmpty() && !widthDimension.isEmpty()) {

                    editor.putString("lengthDimension", "" + seekBar.getProgress());
                    editor.apply();

                    lengthDimension = sharedPreferences.getString("lengthDimension", "");
                }

                if (!circleContent.equals("")) {
                    if (formula.equals("diameter")) {
                        try {
                            solving = 2 * Float.parseFloat(dimension1);
                            text = "diameter = 2 * radius = 2 * " + dimension1 + " = " + solving;
                            formulaText.setText(text);
                            imageView.setImageResource(R.drawable.circle_diameter);
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
                            imageView.setImageResource(R.drawable.circle_area);
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
                            imageView.setImageResource(R.drawable.square_perimeter);
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
                            imageView.setImageResource(R.drawable.square_area);
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
                            imageView.setImageResource(R.drawable.rectangle_perimeter);
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
                            imageView.setImageResource(R.drawable.rectangle_area);
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
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {


                    imageView.getLayoutParams().height = progress;
                    imageView.getLayoutParams().width = progress;
                    imageView.requestLayout();

                    int val = (progress * (seekBar2.getWidth() - 2 * seekBar2.getThumbOffset())) / seekBar2.getMax();
                    String text = "" + progress;
                    seekBarText2.setText(text);
                    seekBarText2.setX(seekBar2.getX() + val + (float) seekBar2.getThumbOffset() / 2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (!lengthDimension.isEmpty() && !widthDimension.isEmpty()) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("widthDimension", "" + seekBar.getProgress());
                        editor.apply();

                        widthDimension = sharedPreferences.getString("widthDimension", "");
                }

                if (!circleContent.equals("")) {
                    if (formula.equals("diameter")) {
                        try {
                            solving = 2 * Float.parseFloat(dimension1);
                            text = "diameter = 2 * radius = 2 * " + dimension1 + " = " + solving;
                            formulaText.setText(text);
                            imageView.setImageResource(R.drawable.circle_diameter);
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
                            imageView.setImageResource(R.drawable.circle_area);
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
                            imageView.setImageResource(R.drawable.square_perimeter);
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
                            imageView.setImageResource(R.drawable.square_area);
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
                            imageView.setImageResource(R.drawable.rectangle_perimeter);
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
                            imageView.setImageResource(R.drawable.rectangle_area);
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
        });

        String seekText, seekText2;

        if (!circleContent.equals("")) {

            seekText = "radius: 100";
            minText.setText(seekText);

            if (formula.equals("diameter")) {
                try {
                    solving = 2 * Float.parseFloat(dimension1);
                    text = "diameter = 2 * radius = 2 * " + dimension1 + " = " + solving;
                    formulaText.setText(text);
                    imageView.setImageResource(R.drawable.circle_diameter);
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
                    imageView.setImageResource(R.drawable.circle_area);
                } catch (NumberFormatException e) {
                    builder1.setMessage("Couldn't transform into float!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        } else if (!squareContent.equals("")) {

            seekText = "side: 100";
            minText.setText(seekText);

            if (formula.equals("perimeter")) {
                try {
                    solving = 4 * Float.parseFloat(dimension1);
                    text = "perimeter = 4 * side = 4 * " + dimension1 + " = " + solving;
                    formulaText.setText(text);
                    imageView.setImageResource(R.drawable.square_perimeter);
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
                    imageView.setImageResource(R.drawable.square_area);
                } catch (NumberFormatException e) {
                    builder1.setMessage("Couldn't transform into float!");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        } else if (!lengthContent.equals("") && !widthContent.equals("")) {

            seekText = "length: 100";
            minText.setText(seekText);

            seekBar2.setVisibility(View.VISIBLE);
            seekBarText2.setVisibility(View.VISIBLE);
            minText2.setVisibility(View.VISIBLE);
            maxText2.setVisibility(View.VISIBLE);
            seekText2 = "width: 100";
            minText2.setText(seekText2);

            if (formula.equals("perimeter")) {
                try {
                    solving = 2 * (Float.parseFloat(lengthDimension) + Float.parseFloat(widthDimension));
                    text = "perimeter = 2 * (length + width) = 2 * (" + lengthDimension + " + "
                            + widthDimension + ") = " + solving;
                    formulaText.setText(text);
                    imageView.setImageResource(R.drawable.rectangle_perimeter);
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
                    imageView.setImageResource(R.drawable.rectangle_area);
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