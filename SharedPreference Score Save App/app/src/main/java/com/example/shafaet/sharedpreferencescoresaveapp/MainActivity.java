package com.example.shafaet.sharedpreferencescoresaveapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewScoreValue;
    private Button buttonIncrease, buttonDecrease;
    private int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewScoreValue = findViewById(R.id.id_tv_scoreValue);
        buttonIncrease = findViewById(R.id.id_btn_increase);
        buttonDecrease = findViewById(R.id.id_btn_decrease);

        textViewScoreValue.setText(String.valueOf(loadScore()));
        score = loadScore();

        buttonIncrease.setOnClickListener(this);
        buttonDecrease.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_increase:
                score++;
                saveScore(score);
                textViewScoreValue.setText(String.valueOf(loadScore()));
                break;
            case R.id.id_btn_decrease:
                score--;
                saveScore(score);
                textViewScoreValue.setText(String.valueOf(loadScore()));
                break;
        }
    }

    private void saveScore(int score){
        SharedPreferences sharedPreferences = getSharedPreferences("saveScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastScore", score);
        editor.commit();
    }

    private int loadScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("saveScore", Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt("lastScore", 0);
        return score;
    }
}
