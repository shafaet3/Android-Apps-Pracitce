package com.example.shafaet.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private Button buttonGuess;
    private int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.id_et_number);
        buttonGuess = findViewById(R.id.id_btn_guess);

        Random random = new Random();
        randomNumber = random.nextInt(20) + 1;

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(editTextNumber.getText().toString());

                if (number > randomNumber) {
                    makeToast("Lower");
                } else if (number < randomNumber) {
                    makeToast("Higher");
                } else {
                    makeToast("That's it! Try again..");
                    editTextNumber.setText("");
                    Random random = new Random();
                    randomNumber = random.nextInt(20) + 1;

                }
            }
        });


    }

    private void makeToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
