package com.example.shafaet.flagname;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {

    private long menuId;

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public DescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null){
            menuId = savedInstanceState.getLong("menuId");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null){
            TextView textViewCountryName = view.findViewById(R.id.id_country_name);
            ImageView imageViewFlagImage = view.findViewById(R.id.id_imgage_flag);
            Country country = Country.countries[(int) menuId];
            textViewCountryName.setText(country.getName().toString());
            imageViewFlagImage.setImageResource(country.getImageId());
        }
    }
}
