package com.example.shafaet.dicegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCpu, textViewYou;
    private ImageView imageViewCpu, imageViewYou;
    private int cpuPoint = 0, yourPoint = 0;
    private Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCpu = findViewById(R.id.id_tv_cpu);
        textViewYou = findViewById(R.id.id_tv_you);
        imageViewCpu = findViewById(R.id.id_iv_cpu);
        imageViewYou = findViewById(R.id.id_iv_you);

        imageViewYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random = new Random();
                int cpuThrow = random.nextInt(6)+1;
                int yourThrow = random.nextInt(6)+1;
                setImageCpu(cpuThrow);
                setImageYou(yourThrow);
                if(cpuThrow > yourThrow){
                    cpuPoint++;
                    textViewCpu.setText("CPU: "+cpuPoint);
                }
                else if(yourThrow > cpuThrow){
                    yourPoint++;
                    textViewYou.setText("YOU: "+yourPoint);
                }
                else{
                    Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_LONG).show();
                }
                Animation animationRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                imageViewCpu.startAnimation(animationRotate);
                imageViewYou.startAnimation(animationRotate);
            }
        });
    }

    public void setImageCpu(int num){
        switch (num) {
            case 1:
                imageViewCpu.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imageViewCpu.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageViewCpu.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imageViewCpu.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imageViewCpu.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imageViewCpu.setImageResource(R.drawable.dice6);
                break;
        }

    }

    public void setImageYou(int num){
        switch (num) {
            case 1:
                imageViewYou.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imageViewYou.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageViewYou.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imageViewYou.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imageViewYou.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imageViewYou.setImageResource(R.drawable.dice6);
                break;
        }

    }
}
