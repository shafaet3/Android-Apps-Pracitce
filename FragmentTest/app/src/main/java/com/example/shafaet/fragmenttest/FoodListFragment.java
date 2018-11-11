package com.example.shafaet.fragmenttest;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodListFragment extends ListFragment {

    interface FoodListListener{
        void onItemClick(long id);
    }

    FoodListListener foodListListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.foodListListener = (FoodListListener) activity;

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(foodListListener != null){
            foodListListener.onItemClick(id);
        }
        super.onListItemClick(l, v, position, id);
    }
    public FoodListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] names = new String[Menu.menus.length];
        for(int i = 0; i < names.length; i++){
            names[i] = Menu.menus[i].getName().toString();
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(arrayAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
