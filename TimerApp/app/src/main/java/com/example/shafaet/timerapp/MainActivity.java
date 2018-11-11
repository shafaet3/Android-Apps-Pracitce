package com.example.shafaet.timerapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private ImageView imageView;
    private int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.id_tv_number);
        imageView = findViewById(R.id.id_iv_play);

        imageView.setOnClickListener(this);

        counter = 0;
    }

    @Override
    public void onClick(View v) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf(counter));
                counter++;
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }
}
