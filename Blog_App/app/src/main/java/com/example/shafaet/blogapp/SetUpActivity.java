package com.example.shafaet.blogapp;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;

import com.google.firebase.auth.FirebaseAuth;

public class SetUpActivity extends AppCompatActivity {

    private Toolbar toolbarSetUp;
    private ImageView imageViewProfileImage;
    private EditText editTextName;
    private Button buttonSaveSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        toolbarSetUp = findViewById(R.id.id_toolbar_setup);
        setSupportActionBar(toolbarSetUp);
        getSupportActionBar().setTitle("Account Setup");

        

        imageViewProfileImage = findViewById(R.id.id_iv_profile_img);
        editTextName = findViewById(R.id.id_et_name);
        buttonSaveSettings = findViewById(R.id.id_btn_save_settings);

        buttonSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                if(!TextUtils.isEmpty(name)){
                    
                }
            }
        });

        imageViewProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ContextCompat.checkSelfPermission(SetUpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(SetUpActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(SetUpActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    }
                    else {
                        BringImagePicker();
                    }
                }
                else {
                    BringImagePicker();
                }
            }
        });
    }

    private void BringImagePicker() {

    }
}
