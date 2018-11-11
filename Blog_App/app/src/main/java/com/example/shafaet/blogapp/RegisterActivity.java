package com.example.shafaet.blogapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextRegEmail, editTextRegPassword;
    private Button buttonRegSignIn, buttonRegRegister;
    private ProgressBar progressBarReg;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        editTextRegEmail = findViewById(R.id.id_et_reg_email);
        editTextRegPassword = findViewById(R.id.id_et_reg_password);
        buttonRegSignIn = findViewById(R.id.id_btn_reg_login);
        buttonRegRegister = findViewById(R.id.id_btn_reg_register);
        progressBarReg = findViewById(R.id.id_pb_login_reg_progress);

        buttonRegSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonRegRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regEmail = editTextRegEmail.getText().toString();
                String regPassword = editTextRegPassword.getText().toString();

                if(!TextUtils.isEmpty(regEmail) && !TextUtils.isEmpty(regPassword)){

                    progressBarReg.setVisibility(View.VISIBLE);

                    mAuth.createUserWithEmailAndPassword(regEmail, regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intentSetup = new Intent(RegisterActivity.this, SetUpActivity.class);
                                startActivity(intentSetup);
                                finish();
                            }
                            else{
                               String errorMessage = task.getException().getMessage();
                                Toast.makeText(RegisterActivity.this, "Error: "+errorMessage, Toast.LENGTH_SHORT).show();
                            }
                            progressBarReg.setVisibility(View.INVISIBLE);

                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            sendToMainActivity();
        }
        else{

        }
    }

    private void sendToMainActivity() {
        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
