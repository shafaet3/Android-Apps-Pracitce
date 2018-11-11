package com.example.shafaet.a4fadinganimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewTom, imageViewJerry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewTom = findViewById(R.id.id_iv_tom);
        imageViewJerry = findViewById(R.id.id_iv_jerry);

        //it will translate tom image by 1000 pixel left and remove from screen
        imageViewTom.setTranslationX(-1000f);

        imageViewJerry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                //alpha() is transparency. Here Tom image will fade in
                imageViewTom.animate().alpha(0f).setDuration(2000);
                //alpha() is transparency. Here Tom image will fade out
                imageViewJerry.animate().alpha(1).setDuration(2000);
                */

                /*
                //Tom image will translate right by 10000 pixel and remove from screen
                imageViewTom.animate().translationXBy(1000f).setDuration(2000);
                //Tom image will translate left by 10000 pixel and remove from screen
                imageViewTom.animate().translationXBy(-1000f).setDuration(2000);
                //Tom image will translate down by 10000 pixel and remove from screen
                imageViewTom.animate().translationYBy(1000f).setDuration(2000);
                //Tom image will translate up by 10000 pixel and remove from screen
                imageViewTom.animate().translationYBy(-1000f).setDuration(2000);
                */
            }
        });

    }
}
