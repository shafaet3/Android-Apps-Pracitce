package com.example.shafaet.a2numberchecking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Number number;
    private EditText editTextNumber;
    private Button buttonCheckNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.id_et_number);
        buttonCheckNumber = findViewById(R.id.id_btn_checkNumber);

        buttonCheckNumber.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_btn_checkNumber){
            if(!editTextNumber.getText().toString().isEmpty()){
                testNumber();
            }
            else{
                Toast.makeText(this, "Enter a Number", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void testNumber() {
        number = new Number();
        number.number = Integer.parseInt(editTextNumber.getText().toString());
        String msg;
        if(number.isTriangle()) {
            if(number.isSquare()) {
                msg = "Number is Triangle and Square";
            }
            else{
                msg = "Number is Triangle but not Square";
            }
        }
        else{
            if(number.isSquare()) {
                msg = "Number is Square but not Triangle";
            }
            else{
                msg = "Number is not Triangle and not Square";
            }
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
