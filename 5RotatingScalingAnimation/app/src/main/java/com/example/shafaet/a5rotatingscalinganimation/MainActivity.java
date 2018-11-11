package com.example.shafaet.a5rotatingscalinganimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewJerry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewJerry = findViewById(R.id.id_iv_jerry);

        imageViewJerry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //It will rotate Jerry image by 1800 degree in 2 second
                imageViewJerry.animate().rotation(1800f).setDuration(2000);
                //It will scale X and Y of Jerry image by 0.5 so that image will become
                // half of original size
                imageViewJerry.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
            }
        });
    }
}
