package com.example.shafaet.flagname;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends ListFragment {


    public interface CountryListListener{
        void onItemClick(long id);
    }
    CountryListListener countryListListener;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(countryListListener != null){
            countryListListener.onItemClick(id);
        }
        super.onListItemClick(l, v, position, id);
    }

    public CountryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] countryNames = new String[Country.countries.length];
        for(int i = 0; i < countryNames.length; i++){
            countryNames[i] = Country.countries[i].getName();
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, countryNames);
        setListAdapter(arrayAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        this.countryListListener = (CountryListListener) activity;
    }
}
