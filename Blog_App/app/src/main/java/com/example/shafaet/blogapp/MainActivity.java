package com.example.shafaet.blogapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        toolbarMain = findViewById(R.id.id_tb_main_toolbar);
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("Home");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // User is not signed in so send to login page
            sendToLoginActivity();
        }
    }

    private void sendToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_action_logout_btn:
                logout();
                return true;
            case R.id.id_action_acc_setting_btn:
                sendToSetUpActivity();
                return true;
            default:
                return false;
        }

    }

    private void sendToSetUpActivity() {
        Intent intentSetUpAccount = new Intent(MainActivity.this, SetUpActivity.class);
        startActivity(intentSetUpAccount);
    }

    private void logout() {
        mAuth.signOut();
        sendToLoginActivity();
    }
}
