package com.example.shafaet.sharedpreferenceapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewUserDetails;
    private EditText editTextUserName, editTextPassword;
    private Button buttonSave, buttonDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewUserDetails = findViewById(R.id.id_tv_userDetails);
        editTextUserName = findViewById(R.id.id_et_userName);
        editTextPassword = findViewById(R.id.id_et_password);
        buttonSave = findViewById(R.id.id_btn_save);
        buttonDisplay = findViewById(R.id.id_btn_display);

        buttonSave.setOnClickListener(this);
        buttonDisplay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();

        switch (v.getId()) {
            case R.id.id_btn_save:
                if (userName.equals("") || password.equals("")) {
                    Toast.makeText(this, "Enter Username and Password", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userNameKey", userName);
                    editor.putString("passwordKey", password);
                    editor.commit();
                    editTextUserName.setText("");
                    editTextPassword.setText("");
                    Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_btn_display:
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                userName = sharedPreferences.getString("userNameKey","Data not found");
                password = sharedPreferences.getString("passwordKey","Data not found");
                textViewUserDetails.setText("User Name: "+userName+"\n"+"Password: "+password);
                break;
        }
    }
}
