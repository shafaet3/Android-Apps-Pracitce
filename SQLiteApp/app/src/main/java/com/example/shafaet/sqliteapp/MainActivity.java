package com.example.shafaet.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName, editTextAge, editTextGender, editTextId;
    private Button buttonAddData, buttonShowData, buttonUpdateData, buttonDeleteData;
    private DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.getWritableDatabase();

        editTextId = findViewById(R.id.id_et_id);
        editTextName = findViewById(R.id.id_et_name);
        editTextAge = findViewById(R.id.id_et_age);
        editTextGender = findViewById(R.id.id_et_gender);
        buttonAddData = findViewById(R.id.id_btn_addData);
        buttonShowData = findViewById(R.id.id_btn_showData);
        buttonUpdateData = findViewById(R.id.id_btn_UpdateData);
        buttonDeleteData = findViewById(R.id.id_btn_deleteData);

        buttonAddData.setOnClickListener(this);
        buttonShowData.setOnClickListener(this);
        buttonUpdateData.setOnClickListener(this);
        buttonDeleteData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String age = editTextAge.getText().toString();
        String gender = editTextGender.getText().toString();
        switch (v.getId()) {
            case R.id.id_btn_addData:
                long rowId = dataBaseHelper.insertData(name, age, gender);
                if (rowId == -1) {
                    Toast.makeText(this, "Data Not inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Data inserted", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.id_btn_showData:
                Cursor cursor = dataBaseHelper.showData();
                if (cursor.getCount() == 0) {
                    displayMessage("Error", "Data Not Found");
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()) {
                        stringBuffer.append("Id:" + cursor.getString(0) + "\n");
                        stringBuffer.append("Name:" + cursor.getString(1) + "\n");
                        stringBuffer.append("Age:" + cursor.getString(2) + "\n");
                        stringBuffer.append("Gender" + cursor.getString(3) + "\n\n\n");
                    }
                    displayMessage("Student Details", stringBuffer.toString());
                }
                break;
            case R.id.id_btn_UpdateData:
                Boolean isUpdated = dataBaseHelper.updateData(id, name, age, gender);
                if (isUpdated == true) {
                    Toast.makeText(this, "Data Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Data not Updated", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.id_btn_deleteData:
                int noOfRowAffected = dataBaseHelper.deleteData(id);
                if (noOfRowAffected == 0) {
                    Toast.makeText(this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Data Deleted", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    public void displayMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }

}
