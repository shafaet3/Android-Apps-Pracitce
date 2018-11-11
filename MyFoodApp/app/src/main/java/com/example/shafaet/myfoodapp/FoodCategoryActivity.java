package com.example.shafaet.myfoodapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FoodCategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        ArrayAdapter<Food> arrayAdapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1,Food.foods);
        listView.setAdapter(arrayAdapter);
    }
}
