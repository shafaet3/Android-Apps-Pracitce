package com.example.shafaet.a1currencyconverterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText editTextDollar;
    private TextView textViewTaka;
    private Button buttonConvert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDollar = findViewById(R.id.id_et_dollarValue);
        textViewTaka = findViewById(R.id.id_tv_takaValue);
        buttonConvert = findViewById(R.id.id_btn_convert);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dollarValue = Double.parseDouble(editTextDollar.getText().toString());
                double takaValue = dollarValue * 83.40;
                editTextDollar.setText("");
                textViewTaka.setText(String.valueOf(String.format("%.2f", takaValue)) + " BDT");
            }
        });
    }
}
