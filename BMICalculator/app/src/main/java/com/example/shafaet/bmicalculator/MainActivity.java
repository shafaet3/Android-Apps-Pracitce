package com.example.shafaet.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateBmi(View view) {
        EditText editTextHeight = findViewById(R.id.id_et_height);
        EditText editTextWeight = findViewById(R.id.id_et_weight);
        TextView textViewBmi = findViewById(R.id.id_tv_bmi);

        double height = Double.parseDouble(editTextHeight.getText().toString());
        double weight = Double.parseDouble(editTextWeight.getText().toString());

        double bmi = weight / (height * height);

        textViewBmi.setText(Double.toString(bmi));
    }
}
